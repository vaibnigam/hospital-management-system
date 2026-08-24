# Hospital Management System

A console-based Hospital Management System built in core Java, introducing class inheritance as its central design concept alongside the `HashMap`-backed, multi-entity architecture developed across the earlier projects in this series. Four distinct staff/patient roles share a common base class, and a service layer coordinates across five interdependent entities to enforce hospital-specific business rules — appointment scheduling, billing, and doctor reassignment.

## Overview

This application allows hospital administrators to manage patients, doctors, nurses, and admin staff; schedule, complete, and cancel appointments; generate and settle patient bills; and reassign a patient's doctor. `Patient`, `Doctor`, `Nurse`, and `Admin` all extend a common abstract `Person` class, demonstrating inheritance and polymorphism in a real, non-trivial domain rather than an isolated exercise.

## Architecture

```
Main (Presentation) → Service Layer → Repository Layer → Model Layer
```

### Model Layer (`com.hospital.model`)

```
Person (abstract)
├── Patient extends Person
├── Doctor extends Person
├── Nurse extends Person
└── Admin extends Person

Appointment (standalone)
Billing (standalone)

Enums: AdminAccessLevel, AppointmentStatus, BillingStatus, ShiftType
```

| Class | Own fields (beyond inherited `id`, `name`, `contactNumber`, `age`) |
|---|---|
| `Person` | Abstract base; declares `abstract String getRole()`, implemented differently by every subclass |
| `Patient` | `diseaseInfo`, `assignedDoctorId` (defaults to `"Not Assigned"` at creation), `bloodGroup`, `admissionDate` (auto-set) |
| `Doctor` | `specialization`, `consultationFee`, `experienceYears` |
| `Nurse` | `department`, `shiftTiming` (`ShiftType`) |
| `Admin` | `accessLevel` (`AdminAccessLevel`) |
| `Appointment` | `appointmentId`, `patientId`, `doctorId`, `appointmentDate`, `status` (auto-`SCHEDULED` at creation) |
| `Billing` | `billId`, `patientId`, `amount`, `status` (auto-`PENDING` at creation), `billingDateTime` (auto-set) |

### Repository Layer (`com.hospital.repository`)

| Repository | Backing structure |
|---|---|
| `PatientRepository`, `DoctorRepository`, `NurseRepository`, `AdminRepository` | `Map<String, T>` keyed by ID |
| `AppointmentRepository` | `Map<String, Appointment>`, plus `findByPatientId()` / `findByDoctorId()` linear scans |
| `BillingRepository` | `Map<String, List<Billing>>` keyed by patient ID (an append-only ledger per patient), plus `findByBillId()` for the cases where only the bill ID is known |

### Service Layer (`com.hospital.service`)

| Service | Depends on | Responsibility |
|---|---|---|
| `DoctorService` | — | Doctor registration and lookup; the only fully independent service |
| `PatientService` | `DoctorService` | Patient registration; `reassignDoctor()` validates the new doctor exists before updating the patient record |
| `NurseService` | — | Nurse registration and lookup |
| `AdminService` | — | Admin registration and lookup |
| `AppointmentService` | `PatientService`, `DoctorService` | Schedules appointments only when both the patient and doctor exist and the date is in the future; enforces valid state transitions for completing/cancelling |
| `BillingService` | `PatientService` | Generates and settles bills, tied to an existing patient |

### Loader (`com.hospital.loader`)

`DataLoader` parses four seed files independently and forwards each parsed row to the corresponding service's `add___()` method, reusing the exact same validation path that a manually-entered record would go through.

### Presentation (`com.hospital.main`)

`Main` wires all six services together — in dependency order, since `PatientService`, `AppointmentService`, and `BillingService` each require other services already constructed — and exposes a 24-option console menu covering every operation below.

## Features

- Add a patient, doctor, nurse, or admin (each independently validated)
- View all patients / doctors / nurses / admins
- Search a patient, doctor, nurse, or admin by ID
- Search patients by name (partial match)
- Schedule an appointment — rejected unless both patient and doctor exist and the date is in the future
- Complete or cancel an appointment — rejected if it is already in a terminal state
- View appointments by patient, by doctor, or all appointments
- Reassign a patient's doctor — validates the new doctor exists
- Generate a bill for a patient
- Mark a bill as paid — rejected if already paid
- View a patient's full billing history
- Bulk-load patients, doctors, nurses, and admins from `.txt` files at startup

## Project Structure

```
src/
  com/hospital/model/       → Person.java, Patient.java, Doctor.java, Nurse.java, Admin.java,
                               Appointment.java, Billing.java,
                               AdminAccessLevel.java, AppointmentStatus.java, BillingStatus.java, ShiftType.java
  com/hospital/repository/  → PatientRepository.java, DoctorRepository.java, NurseRepository.java,
                               AdminRepository.java, AppointmentRepository.java, BillingRepository.java
  com/hospital/service/     → PatientService.java, DoctorService.java, NurseService.java, AdminService.java,
                               AppointmentService.java, BillingService.java
  com/hospital/loader/      → DataLoader.java
  com/hospital/main/        → Main.java
data/
  doctors.txt                 → sample doctor data loaded at startup
  patients.txt                → sample patient data loaded at startup
  nurses.txt                   → sample nurse data loaded at startup
  admins.txt                    → sample admin data loaded at startup
```

## Data File Formats

`data/doctors.txt`:
```
name,contactNumber,age,specialization,consultationFee,experienceYears
```

`data/patients.txt`:
```
name,contactNumber,age,diseaseInfo,bloodGroup
```

`data/nurses.txt`:
```
name,contactNumber,age,department,shiftTiming
```
(`shiftTiming` is one of `MORNING`, `EVENING`, `NIGHT`)

`data/admins.txt`:
```
name,contactNumber,age,accessLevel
```
(`accessLevel` is one of `BASIC`, `BILLING`, `SUPER_ADMIN`)

Example (`doctors.txt`):
```
Ramesh Gupta,9876543210,45,Cardiology,800,20
Sunita Rao,9823456781,38,Dermatology,500,12
```

## Tech Stack

- Java 17
- `java.util` Collections (`HashMap`, `ArrayList`, `List`, `Map`)
- `java.time.LocalDate` / `LocalDateTime` — admission dates, appointment scheduling, billing timestamps
- Console I/O via `Scanner`

## Running the Project

1. Import the project into Eclipse (or any Java IDE).
2. Ensure `data/doctors.txt`, `data/patients.txt`, `data/nurses.txt`, and `data/admins.txt` exist in the project root.
3. Run `Main.java`.

## Design Notes

### Inheritance models a genuine "is-a" relationship, not just shared fields
`Patient`, `Doctor`, `Nurse`, and `Admin` all *are* a `Person` — they share identity, contact information, and age, but each plays a distinct role. `Person` is declared `abstract` specifically because no one is ever "just a Person" in this domain; a bare `Person` object is never meaningful, only its four concrete roles are. The `abstract getRole()` method forces every subclass to declare its own identity and enables true polymorphism: any `List<Person>` can be iterated and each element will report its own correct role without a single `instanceof` check.

### `toString()` composition via `super.toString()`
Every subclass overrides `toString()` by calling `super.toString()` first and appending its own fields, rather than reimplementing the common fields from scratch. This means a change to how `Person`'s common fields are displayed automatically propagates to all four subclasses without touching their code.

### Validation stays in the service layer, consistently
As in the earlier projects, no model class validates its own data and no repository makes a decision about whether to store something. Every `addX()` method in every service applies the same three baseline checks (non-empty name, a realistic age range, and a 10-digit mobile number starting with 6-9) before constructing the model object, keeping that logic in one predictable place per entity.

### Auto-generated identifiers, not user-supplied
As in the Bank Account Management System, no `add___()` method accepts an ID as a parameter. Each service maintains its own internal counter (`P1, P2...` for patients, `D1, D2...` for doctors, and so on) and generates the identifier itself, making duplicate IDs structurally impossible.

### Optional relationships default to a placeholder, not a forced input
A new patient does not need a doctor assigned at registration — `assignedDoctorId` defaults to `"Not Assigned"` in the constructor, mirroring the "Not Allocated" pattern used for room numbers in the Hostel Management System. Assignment happens later, on demand, through `reassignDoctor()`, which validates that the target doctor actually exists before updating the patient record.

### Services depending on other services, not just repositories
`PatientService`, `AppointmentService`, and `BillingService` each hold a reference to one or more *other services* — not their repositories — to perform existence checks (e.g. "does this doctor exist?"). This keeps each service's own validation rules encapsulated behind its public methods rather than duplicating repository-lookup logic across services, at the cost of requiring careful object-construction order in `Main` (independent services first, dependent services after).

### An append-only ledger, searchable by ID it wasn't indexed on
`BillingRepository` indexes bills by patient ID for the common case (a patient's full billing history), but `markAsPaid()` only has a bill ID to work with. Rather than maintaining a second index, `findByBillId()` performs a linear scan across every patient's bill list — a deliberate trade-off favoring a simpler data structure over a second `Map` that would need to be kept in sync with the first.

## Summary

This is the fourth project in the series and the first to introduce inheritance as a core structural pattern, alongside the most extensive service-to-service coordination attempted so far. It builds directly on the `HashMap`-backed, cross-entity architecture from the Hostel and Bank projects, while adding a genuine class hierarchy, polymorphic behavior through an abstract method, and a service layer where dependencies between services — not just between a service and its own repository — had to be deliberately designed and ordered.
