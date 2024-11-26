import kotlin.random.Random

enum class Illness(val description: String) {
    FLU("Common flu with symptoms like fever, cough"),
    COVID("Severe respiratory illness caused by the virus SARS-CoV-2"),
    COLD("Mild respiratory illness with runny nose and sore throat")
}

abstract class Person(val name: String) {
    abstract fun introduce(): String
}

interface Staff {
    val salary: Double
    fun work(): String
}

class Department(private val departmentName: String, val responsibility: String) {
    private val workers: MutableList<Worker> = mutableListOf()

    fun addWorker(worker: Worker) {
        workers.add(worker)
    }

    fun getWorkers(): List<Worker> = workers

    fun totalSalaries(): Double {
        return workers.sumOf { it.salary }
    }

    fun showWorkers() {
        println("Workers in $departmentName:")
        workers.forEach { println(it.introduce()) }
    }
}

open class Worker(name: String, override val salary: Double) : Person(name), Staff {
    lateinit var department: Department

    override fun introduce(): String {
        return "Hi, I am $name, a worker earning $$salary."
    }

    override fun work(): String {
        return "$name is working in ${department.responsibility}."
    }
}

class Doctor(name: String, salary: Double, val specialization: String) : Worker(name, salary) {
    override fun work(): String {
        return "$name is treating patients as a $specialization."
    }
}

class Nurse(name: String, salary: Double) : Worker(name, salary) {
    override fun work(): String {
        return "$name is assisting doctors and taking care of patients."
    }
}

class Patient(name: String, var illness: Illness) : Person(name) {
    var isTreated: Boolean = false
        private set

    fun receiveTreatment(worker: Worker): Boolean {
        println("$name is being treated for ${illness.description} by ${worker.name}.")
        isTreated = Random.nextBoolean()
        println(if (isTreated) "$name has recovered." else "$name's treatment was unsuccessful.")
        return isTreated
    }

    override fun introduce(): String {
        return "Hello, my name is $name, and I am a patient with ${illness.description}."
    }
}

class Hospital {
    private val departments = mutableListOf<Department>()
    private val patients = mutableListOf<Patient>()

    fun addDepartment(department: Department) {
        departments.add(department)
    }

    fun admitPatient(patient: Patient) {
        patients.add(patient)
        println("${patient.name} has been admitted to the hospital.")
    }

    fun getAllStaff(): List<Worker> {
        return departments.flatMap { it.getWorkers() }
    }

    fun calculateTotalSalaries(): Double {
        return departments.sumOf { it.totalSalaries() }
    }

    fun treatAllPatients() {
        patients.forEach { patient ->
            val doctor = getAllStaff().filterIsInstance<Doctor>().randomOrNull()
            if (doctor != null) patient.receiveTreatment(doctor)
            else println("No doctor available to treat ${patient.name}.")
        }
    }
}

fun main() {
    val hospital = Hospital()

    val emergencyDepartment = Department("Emergency", "Provide immediate care")
    val generalMedicine = Department("General Medicine", "Overall health care")

    val doctor1 = Doctor("Dr. John", 7000.0, "Cardiology")
    val doctor2 = Doctor("Dr. Jane", 8000.0, "Pediatrics")
    val nurse1 = Nurse("Nurse Alice", 3000.0)
    val nurse2 = Nurse("Nurse Bob", 3200.0)

    emergencyDepartment.addWorker(doctor1)
    emergencyDepartment.addWorker(nurse1)
    generalMedicine.addWorker(doctor2)
    generalMedicine.addWorker(nurse2)

    hospital.addDepartment(emergencyDepartment)
    hospital.addDepartment(generalMedicine)

    val patient1 = Patient("Alice", Illness.FLU)
    val patient2 = Patient("Bob", Illness.COVID)
    val patient3 = Patient("Charlie", Illness.COLD)

    hospital.admitPatient(patient1)
    hospital.admitPatient(patient2)
    hospital.admitPatient(patient3)

    println("Hospital Staff:")
    hospital.getAllStaff().forEach { println(it.introduce()) }

    println("\nTreating all patients:")
    hospital.treatAllPatients()

    println("\nDepartment Salaries:")
    hospital.calculateTotalSalaries().let { println("Total salaries across all departments: $$it") }

    println("\nFiltering and Sorting Example:")
    val highEarningStaff = hospital.getAllStaff().filter { it.salary > 5000 }.sortedByDescending { it.salary }
    highEarningStaff.forEach { println("${it.name} earns $$${it.salary}") }

    println("\nGrouping Example:")
    val groupedStaff = hospital.getAllStaff().groupBy { it.salary > 5000 }
    groupedStaff.forEach { (isHighEarner, staffList) ->
        println(if (isHighEarner) "High earners:" else "Low earners:")
        staffList.forEach { println(it.introduce()) }
    }
}
