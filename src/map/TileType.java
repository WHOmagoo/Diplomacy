package map;

public enum TileType {
    /**
     * This is the three different types of tiles, Landlocked which a navy may not occupy and has no
     * water borders, Coastal which any unit may occupy and has water borders, and water which only
     * allows for navy's to occupy them.
     */
    Landlocked, Coastal, Water
}
