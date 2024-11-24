enum class Illness(val description: String) {
    FLU("Common flu with symptoms like fever, cough"),
    COVID("Severe respiratory illness caused by the virus SARS-CoV-2"),
    COLD("Mild respiratory illness with runny nose and sore throat")
}

class Department(private val departmentName: String, val responsibility: String) {
    var numberOfStaff: Int = 0
        private set

    fun hireStaff(count: Int) {
        numberOfStaff += count
        println("$count staff hired in $departmentName department.")
    }

    fun showDepartmentInfo() {
        println("Department: $departmentName\nResponsibility: $responsibility\nNumber of Staff: $numberOfStaff")
    }
}

open class Worker(val name: String, val salary: Double) {
    lateinit var department: Department
        private set

    var isAvailable: Boolean = true
        get() = field
        set(value) {
            if (value) println("$name is now available.")
            else println("$name is now unavailable.")
            field = value
        }

    fun assignToDepartment(department: Department) {
        this.department = department
        println("$name has been assigned to the ${department.departmentName} department.")
    }

    open fun work(): String {
        return "$name is working."
    }
}

class Patient(val patientName: String, var illness: Illness) {
    var isTreated: Boolean = false
        private set

    constructor(patientName: String) : this(patientName, Illness.COLD)

    fun receiveTreatment(worker: Worker): Boolean {
        println("$patientName is being treated by ${worker.name} for ${illness.description}.")
        if (worker.isAvailable) {
            isTreated = true
            println("$patientName is successfully treated for ${illness.description}.")
        } else {
            println("${worker.name} is not available to treat $patientName.")
        }
        return isTreated
    }
}

class Hospital {
    companion object {
        val hospitalName: String = "City Medical Center"
        var patientCount: Int = 0

        fun addPatient(patient: Patient) {
            patientCount++
            println("${patient.patientName} has been added to the hospital. Total patients: $patientCount")
        }
    }

    val departments: MutableList<Department> = mutableListOf()
    val workers: MutableList<Worker> = mutableListOf()

    fun addDepartment(department: Department) {
        departments.add(department)
    }

    fun addWorker(worker: Worker) {
        workers.add(worker)
    }

    fun treatPatient(patient: Patient, worker: Worker) {
        if (worker is Doctor) {
            val treatmentResult = patient.receiveTreatment(worker)
            println("Treatment Result: ${if (treatmentResult) "Successful" else "Failed"}")
        } else {
            println("Only doctors can treat patients.")
        }
    }
}

class Doctor(name: String, salary: Double) : Worker(name, salary) {
    override fun work(): String {
        return "$name is diagnosing patients."
    }
}

class Nurse(name: String, salary: Double) : Worker(name, salary) {
    override fun work(): String {
        return "$name is assisting in patient care."
    }
}

fun main() {
    val hospital = Hospital()

    val emergencyDepartment = Department("Emergency", "Provide immediate medical assistance")
    val generalMedicineDepartment = Department("General Medicine", "Provide overall health care")

    val doctorJohn = Doctor("Dr. John", 7000.0)
    val nurseAlice = Nurse("Nurse Alice", 3000.0)

    hospital.addDepartment(emergencyDepartment)
    hospital.addDepartment(generalMedicineDepartment)
    hospital.addWorker(doctorJohn)
    hospital.addWorker(nurseAlice)

    emergencyDepartment.hireStaff(3)
    generalMedicineDepartment.hireStaff(2)

    val patient1 = Patient("Alice", Illness.FLU)
    val patient2 = Patient("Bob", Illness.COVID)

    Hospital.addPatient(patient1)
    Hospital.addPatient(patient2)

    doctorJohn.assignToDepartment(emergencyDepartment)
    hospital.treatPatient(patient1, doctorJohn)

    hospital.treatPatient(patient2, nurseAlice)
}
