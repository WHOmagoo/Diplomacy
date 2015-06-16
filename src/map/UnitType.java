package map;

public enum UnitType {
    /**
     * There are three different types of units, an army a navy or empty, each country is occupied
     * by only one of these at a time, an army may not be on water, a navy may not be on landlocked,
     * and empty is wherever there is not unit currently occupied.
     */
    ARMY, NAVY, EMPTY
}