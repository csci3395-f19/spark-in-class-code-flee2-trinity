package basics
import swiftvis2.plotting._
case class TempRow(day:Int, doy: Int, month: Int, year: Int, percip: Double, 
tave: Double, tmax:Double, tmin: Double)

object SATemps{
    def parseLine(line: String): TempRow = {
        val p = line.split(",")
        TempRow(p(0).toInt, p(1).toInt, p(2).toInt, p(4).toInt, p(5).toDouble
        , p(6).toDouble, p(7).toDouble, p(8).toDouble)
    }
    def main(args: Array[String]): Unit={
        val source = scala.io.Source.fromFile("/users/mlewis/CSCI3395-F19/InClassBD/data/SanAntonioTemps.csv")
        val lines = source.getLines()
        
        val data = lines.drop(2).map(parseLine).toArray
        //data.take(5).foreach(println)


        val hotDay1 = data.maxBy(_.tmax)
        val hotDay2 = data.reduce((d1, d2)=> if(d1.tmax>d2.tmax) d1 else d2)
        println(hotDay1)
        println(hotDay2)
        val mostPercipday = data.maxBy(_.percip)
        println(mostPercipday)

        val allDays = data.count(a =>1==1)
        val rainyDays = data. count(a => a.percip>1).toDouble
        println("rain over 1 inch chance"+rainyDays/allDays)
        val rainyDays2 = data.count(a => a.percip>0).toDouble
        //val allRainyDay = data.scan(0.0)((accu, b.tmax) => accu+b.tmax)
        val (allRainyDaySum, allRainyDayCnt) = data.foldLeft(0.0->0){case ((sum, cnt), td)=> 
            if(td.percip>0)(sum+td.tmax, cnt+1) else (sum,cnt)}
        println("average max in rainy day"+ allRainyDaySum.toDouble/allRainyDayCnt)
        val monthGroup = data.groupBy(_.month)
        val avgMonthlyHigh = monthGroup.map{case 
            (m, days)=> m->days.foldLeft(0.0)((sum, td)=>sum+td.tmax)/days.length.toDouble}
        println("avgMonthlyHigh"+avgMonthlyHigh)
        val avgMonthlyPrecip = monthGroup.map{case 
            (m, days)=> m->days.foldLeft(0.0)((sum, td)=>sum+td.percip)/days.length.toDouble}
        println("avgMonthlyPrecip"+avgMonthlyPrecip.toSeq.sortBy(_._1).foreach(println))
        //val medMonthlyPrecip = monthGroup.map{case 
        //    (m, days)=> (m, days.sortBy(_.precip).)}
        val medMonthlyPrecip = monthGroup.mapValues(m=>m.sortBy(_.percip).apply(m.length/2).percip)
        
        println("medMonthlyPrecip"+medMonthlyPrecip.toSeq.sortBy(_._1).foreach(println))
        /*
        val cg = ColorGradient(1946.0 -> RedARGB, 1975.0->BlueARGB, 2014.0->GreenARGB)
        val sizes = data.map(_.precip *2 +2)
        val tempByDayPlot = Plot.simple(
            ScatterStyle(data.map(_.doy), data.map(_.tave), symbolWidth = 3, symbolHeight = 3, colors = cg(data.map(_.year))), 
            "SA Temps","Day of Year", "Temp")
        SwingRenderer(tempByDayPlot, 800, 800, true)
        */
    }
}