package ru.chemelson.patterns.gof

fun main() {
    val csvReport = CSVReportGenerator()
    csvReport.generateReport()
}

abstract class ReportGenerator {
    protected abstract fun collectData(): String
    protected abstract fun processData(data: String): String
    protected abstract fun formatReport(processedData: String): String

    private fun printReport(report: String) {
        println(report)
    }

    fun generateReport() {
        val data = collectData()
        val processedData = processData(data)
        val report = formatReport(processedData)
        printReport(report)
    }
}

// Concrete implementor
class CSVReportGenerator : ReportGenerator() {
    override fun collectData(): String = "CSV Data"

    override fun processData(data: String): String = "Processed $data"

    override fun formatReport(processedData: String): String = "CSV Report: $processedData"
}