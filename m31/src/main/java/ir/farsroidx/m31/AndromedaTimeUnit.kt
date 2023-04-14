package ir.farsroidx.m31

sealed class AndromedaTimeUnit (val value: Int) {
    object Seconds : AndromedaTimeUnit(1)
    object Minutes : AndromedaTimeUnit(60)
    object Hour : AndromedaTimeUnit(3_600)
    object Day : AndromedaTimeUnit(86_400)
    object Week : AndromedaTimeUnit(604_800)
    object Month : AndromedaTimeUnit(18_144_000)
    object Year : AndromedaTimeUnit(217_728_000)
}
