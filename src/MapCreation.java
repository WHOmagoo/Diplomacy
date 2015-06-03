import constants.Team;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import map.*;

public class MapCreation {

    public static Map createMap() {
        ArrayList<Country> tempCountries = createCountries();
        tempCountries = setBorders(tempCountries);
        Map map = new Map(tempCountries);
        setOccupiedBy(tempCountries);
        //Watch out for order of code execution, may cause errors later.
        map.setMapGraphic(new ImageIcon("files\\Map.png"));
        map.setMapText(new ImageIcon("files\\Map Text.png"));
        map.refreshAllCountries();
        map.addAllCountries();
        return map;
    }

    private static ArrayList<Country> createCountries() {
        final String[] COUNTRY_NAME_LIST = {
/*0-4*/     "Off Map", "Liverpool", "Ireland", "Wales", "Edinburgh",
/*5-9*/     "London", "Norway", "Sweden", "Finland", "Vologda",
/*10-14*/   "Leningrad", "Smolensk", "Moscow", "Bellorussia", "Presov",
/*15-19*/   "Kiev", "Holland", "Kiel", "Berlin", "Siliesia",
/*20-24*/   "Rurh", "Munich", "Belgium", "Picardy", "Brest",
/*25-29*/   "Paris", "Marseille", "Gacony", "Bilboa", "Lugo",
/*30-34*/   "Portugal", "Seville", "Valancia", "Barcelona", "Ust",
/*35-39*/   "Pechora", "Komi", "Gorki", "Crimea", "Rostov",
/*40-44*/   "Czechoslovakia", "Austria", "Hungary", "Bosnia", "Albania",
/*45-49*/   "Serbia", "Banat", "Romania", "Bulgaria", "Macedonia",
/*50-54*/   "Greece", "Istanbul", "Samsun", "Kras", "Izmir",
/*55-59*/   "Lebanon", "Israel", "Jordan", "Piedmont", "Venice",
/*60-64*/   "Tuscany", "Rome", "Apulia", "Naples", "Sicily",
/*65-69*/   "Sardinia", "Casablanca", "Algiers", "Setif", "Aflou",
/*70-74*/   "Sahara", "Fazzar", "Murzq", "Bengasi", "Sallum",
/*75-79*/   "Tobruk", "Cairo", "Nile", "Sawhaj", "Crete",
/*80-84*/   "Irish Sea", "North Sea", "Baltic Sea", "English  Channel", "Atlantic Ocean",
/*85-89*/   "Bay of Biscay", "Gibraltar", "Western Mediterranean", "Gulf of Lyons", "Tyrrhenian Sea",
/*90-94*/   "Ionian Sea", "Adriatic Sea", "Agean Sea", "Eastern Mediterranean", "Red Sea",
/*95-97*/   "Lake Cherkassy", "West Black Sea", "East Black Sea"};

        final Point[] locations = new Point[]{
                new Point(0, 0),
                new Point(202, 103),
                new Point(83, 213),
                new Point(201, 166),
                new Point(249, 89),
                new Point(261, 151),
                new Point(368, 48),
                new Point(457, 59),
                new Point(576, 72),
                new Point(665, 99),
                new Point(584, 122),
                new Point(660, 158),
                new Point(727, 167),
                new Point(581, 171),
                new Point(584, 221),
                new Point(643, 218),
                new Point(367, 182),
                new Point(423, 167),
                new Point(483, 166),
                new Point(487, 206),
                new Point(389, 239),
                new Point(435, 255),
                new Point(325, 232),
                new Point(280, 262),
                new Point(217, 280),
                new Point(295, 324),
                new Point(289, 376),
                new Point(226, 351),
                new Point(173, 383),
                new Point(127, 412),
                new Point(77, 449),
                new Point(182, 481),
                new Point(182, 464),
                new Point(237, 414),
                new Point(741, 83),
                new Point(893, 68),
                new Point(821, 106),
                new Point(823, 153),
                new Point(764, 220),
                new Point(868, 216),
                new Point(497, 248),
                new Point(471, 294),
                new Point(577, 270),
                new Point(533, 296),
                new Point(569, 395),
                new Point(581, 348),
                new Point(625, 298),
                new Point(679, 307),
                new Point(693, 355),
                new Point(636, 378),
                new Point(632, 448),
                new Point(764, 372),
                new Point(841, 349),
                new Point(901, 356),
                new Point(763, 426),
                new Point(879, 464),
                new Point(833, 538),
                new Point(867, 592),
                new Point(386, 353),
                new Point(444, 379),
                new Point(406, 398),
                new Point(449, 429),
                new Point(513, 423),
                new Point(509, 468),
                new Point(495, 536),
                new Point(344, 468),
                new Point(117, 597),
                new Point(241, 565),
                new Point(358, 553),
                new Point(185, 635),
                new Point(320, 653),
                new Point(388, 648),
                new Point(473, 657),
                new Point(446, 598),
                new Point(544, 612),
                new Point(625, 567),
                new Point(714, 592),
                new Point(711, 634),
                new Point(613, 661),
                new Point(1, 1),
                new Point(107, 92),
                new Point(315, 98),
                new Point(516, 99),
                new Point(211, 229),
                new Point(79, 295),
                new Point(151, 314),
                new Point(61, 549),
                new Point(261, 495),
                new Point(271, 446),
                new Point(416, 475),
                new Point(562, 488),
                new Point(481, 347),
                new Point(692, 420),
                new Point(800, 474),
                new Point(788, 646),
                new Point(696, 245),
                new Point(782, 313),
                new Point(841, 283),
                new Point(0, 0)};

        ArrayList<Country> tempCountries = new ArrayList<Country>();
        for (int i = 0; i < COUNTRY_NAME_LIST.length; i++) {
            TileType tileType;
            if (i < 80) {
                tileType = TileType.Landlocked;
            } else {
                tileType = TileType.Water;
            }
            tempCountries.add(new Country(COUNTRY_NAME_LIST[i], locations[i], tileType));
        }
        return tempCountries;
    }

    private static ArrayList<Country> setBorders(ArrayList<Country> countries) {
        Border[] tempBorders = new Border[]{
                new Border(countries.get(0), new Country[]{countries.get(6), countries.get(7), countries.get(35), countries.get(53), countries.get(57), countries.get(78), countries.get(72), countries.get(71), countries.get(70), countries.get(30), countries.get(2), countries.get(80), countries.get(97), countries.get(94), countries.get(86), countries.get(84)}),
                new Border(countries.get(1), new Country[]{countries.get(4), countries.get(3), countries.get(80)}),
                new Border(countries.get(2), new Country[]{countries.get(0), countries.get(3), countries.get(80), countries.get(83), countries.get(84)}),
                new Border(countries.get(3), new Country[]{countries.get(1), countries.get(4), countries.get(5), countries.get(2), countries.get(80), countries.get(83)}),
                new Border(countries.get(4), new Country[]{countries.get(1), countries.get(3), countries.get(5), countries.get(6), countries.get(81), countries.get(80)}),
                new Border(countries.get(5), new Country[]{countries.get(3), countries.get(4), countries.get(22), countries.get(81), countries.get(83)}),
                new Border(countries.get(6), new Country[]{countries.get(4), countries.get(0), countries.get(7), countries.get(80), countries.get(81)}),
                new Border(countries.get(7), new Country[]{countries.get(6), countries.get(8), countries.get(0), countries.get(9), countries.get(34), countries.get(35), countries.get(81), countries.get(82)}),
                new Border(countries.get(8), new Country[]{countries.get(9), countries.get(10), countries.get(82), countries.get(7)}),
                new Border(countries.get(9), new Country[]{countries.get(7), countries.get(7), countries.get(34), countries.get(12), countries.get(11), countries.get(8), countries.get(36), countries.get(10)}),
                new Border(countries.get(10), new Country[]{countries.get(8), countries.get(18), countries.get(11), countries.get(9), countries.get(82), countries.get(13)}),
                new Border(countries.get(11), new Country[]{countries.get(10), countries.get(9), countries.get(12), countries.get(15), countries.get(13)}),
                new Border(countries.get(12), new Country[]{countries.get(9), countries.get(36), countries.get(38), countries.get(15), countries.get(11)}),
                new Border(countries.get(13), new Country[]{countries.get(10), countries.get(11), countries.get(15), countries.get(14), countries.get(19), countries.get(18)}),
                new Border(countries.get(14), new Country[]{countries.get(13), countries.get(15), countries.get(19), countries.get(40), countries.get(42)}),
                new Border(countries.get(15), new Country[]{countries.get(14), countries.get(13), countries.get(11), countries.get(12), countries.get(42), countries.get(97), countries.get(95), countries.get(38)}),
                new Border(countries.get(16), new Country[]{countries.get(17), countries.get(20), countries.get(81)}),
                new Border(countries.get(17), new Country[]{countries.get(16), countries.get(81), countries.get(18), countries.get(19), countries.get(20), countries.get(82)}),
                new Border(countries.get(18), new Country[]{countries.get(17), countries.get(19), countries.get(10), countries.get(82), countries.get(13)}),
                new Border(countries.get(19), new Country[]{countries.get(17), countries.get(18), countries.get(20), countries.get(21), countries.get(40), countries.get(13), countries.get(14)}),
                new Border(countries.get(20), new Country[]{countries.get(16), countries.get(17), countries.get(41), countries.get(19), countries.get(21)}),
                new Border(countries.get(21), new Country[]{countries.get(20), countries.get(19), countries.get(40), countries.get(41)}),
                new Border(countries.get(22), new Country[]{countries.get(5), countries.get(81), countries.get(23), countries.get(83)}),
                new Border(countries.get(23), new Country[]{countries.get(22), countries.get(24), countries.get(25), countries.get(26), countries.get(83)}),
                new Border(countries.get(24), new Country[]{countries.get(23), countries.get(25), countries.get(26), countries.get(83), countries.get(85), countries.get(27)}),
                new Border(countries.get(25), new Country[]{countries.get(23), countries.get(26), countries.get(24)}),
                new Border(countries.get(26), new Country[]{countries.get(25), countries.get(23), countries.get(27), countries.get(33), countries.get(24), countries.get(88)}),
                new Border(countries.get(27), new Country[]{countries.get(24), countries.get(26), countries.get(28), countries.get(33), countries.get(85)}),
                new Border(countries.get(28), new Country[]{countries.get(27), countries.get(33), countries.get(32), countries.get(29), countries.get(85)}),
                new Border(countries.get(29), new Country[]{countries.get(28), countries.get(32), countries.get(31), countries.get(30), countries.get(84), countries.get(85)}),
                new Border(countries.get(30), new Country[]{countries.get(29), countries.get(84), countries.get(31), countries.get(0), countries.get(86)}),
                new Border(countries.get(31), new Country[]{countries.get(30), countries.get(32), countries.get(29), countries.get(66), countries.get(86), countries.get(87)}),
                new Border(countries.get(32), new Country[]{countries.get(31), countries.get(29), countries.get(28), countries.get(87), countries.get(33), countries.get(88)}),
                new Border(countries.get(33), new Country[]{countries.get(28), countries.get(27), countries.get(32), countries.get(88), countries.get(26)}),
                new Border(countries.get(34), new Country[]{countries.get(35), countries.get(36), countries.get(9), countries.get(7)}),
                new Border(countries.get(35), new Country[]{countries.get(34), countries.get(0), countries.get(36), countries.get(39), countries.get(97), countries.get(7)}),
                new Border(countries.get(36), new Country[]{countries.get(34), countries.get(35), countries.get(37), countries.get(39), countries.get(38), countries.get(9), countries.get(12)}),
                new Border(countries.get(37), new Country[]{countries.get(36), countries.get(38), countries.get(39)}),
                new Border(countries.get(38), new Country[]{countries.get(39), countries.get(37), countries.get(36), countries.get(12), countries.get(15), countries.get(97)}),
                new Border(countries.get(39), new Country[]{countries.get(35), countries.get(53), countries.get(36), countries.get(37), countries.get(97), countries.get(38)}),
                new Border(countries.get(40), new Country[]{countries.get(19), countries.get(21), countries.get(41), countries.get(42), countries.get(14)}),
                new Border(countries.get(41), new Country[]{countries.get(20), countries.get(21), countries.get(40), countries.get(42), countries.get(43), countries.get(44), countries.get(91)}),
                new Border(countries.get(42), new Country[]{countries.get(40), countries.get(14), countries.get(15), countries.get(46), countries.get(45), countries.get(44), countries.get(95), countries.get(43), countries.get(41)}),
                new Border(countries.get(43), new Country[]{countries.get(41), countries.get(42), countries.get(44)}),
                new Border(countries.get(44), new Country[]{countries.get(42), countries.get(91), countries.get(41), countries.get(43), countries.get(45), countries.get(50)}),
                new Border(countries.get(45), new Country[]{countries.get(42), countries.get(44), countries.get(46), countries.get(47), countries.get(50), countries.get(44)}),
                new Border(countries.get(46), new Country[]{countries.get(42), countries.get(45), countries.get(47), countries.get(95)}),
                new Border(countries.get(47), new Country[]{countries.get(46), countries.get(48), countries.get(49), countries.get(50), countries.get(96), countries.get(45), countries.get(95)}),
                new Border(countries.get(48), new Country[]{countries.get(47), countries.get(49), countries.get(96), countries.get(92), countries.get(50), countries.get(51)}),
                new Border(countries.get(49), new Country[]{countries.get(48), countries.get(47), countries.get(50)}),
                new Border(countries.get(50), new Country[]{countries.get(49), countries.get(48), countries.get(47), countries.get(91), countries.get(44), countries.get(45), countries.get(92)}),
                new Border(countries.get(51), new Country[]{countries.get(48), countries.get(52), countries.get(92), countries.get(96), countries.get(54), countries.get(97)}),
                new Border(countries.get(52), new Country[]{countries.get(51), countries.get(53), countries.get(54), countries.get(55), countries.get(97)}),
                new Border(countries.get(53), new Country[]{countries.get(52), countries.get(55), countries.get(57), countries.get(0), countries.get(97), countries.get(39)}),
                new Border(countries.get(54), new Country[]{countries.get(51), countries.get(93), countries.get(92), countries.get(52), countries.get(55), countries.get(56)}),
                new Border(countries.get(55), new Country[]{countries.get(54), countries.get(56), countries.get(57), countries.get(52), countries.get(53)}),
                new Border(countries.get(56), new Country[]{countries.get(54), countries.get(55), countries.get(93), countries.get(57), countries.get(76), countries.get(94)}),
                new Border(countries.get(57), new Country[]{countries.get(56), countries.get(55), countries.get(53), countries.get(0), countries.get(94)}),
                new Border(countries.get(58), new Country[]{countries.get(60), countries.get(59), countries.get(88), countries.get(89)}),
                new Border(countries.get(59), new Country[]{countries.get(60), countries.get(91), countries.get(58), countries.get(62), countries.get(61), countries.get(90)}),
                new Border(countries.get(60), new Country[]{countries.get(58), countries.get(59), countries.get(61), countries.get(89)}),
                new Border(countries.get(61), new Country[]{countries.get(60), countries.get(63), countries.get(62), countries.get(59), countries.get(89)}),
                new Border(countries.get(62), new Country[]{countries.get(59), countries.get(61), countries.get(63), countries.get(90)}),
                new Border(countries.get(63), new Country[]{countries.get(62), countries.get(61), countries.get(64), countries.get(90), countries.get(89)}),
                new Border(countries.get(64), new Country[]{countries.get(73), countries.get(63), countries.get(89), countries.get(90)}),
                new Border(countries.get(65), new Country[]{countries.get(67), countries.get(89), countries.get(87)}),
                new Border(countries.get(66), new Country[]{countries.get(67), countries.get(31), countries.get(69), countries.get(87), countries.get(86)}),
                new Border(countries.get(67), new Country[]{countries.get(87), countries.get(66), countries.get(68), countries.get(65), countries.get(69), countries.get(89)}),
                new Border(countries.get(68), new Country[]{countries.get(67), countries.get(69), countries.get(70), countries.get(71), countries.get(89), countries.get(73)}),
                new Border(countries.get(69), new Country[]{countries.get(66), countries.get(67), countries.get(68), countries.get(70), countries.get(86)}),
                new Border(countries.get(70), new Country[]{countries.get(69), countries.get(68), countries.get(71), countries.get(0)}),
                new Border(countries.get(71), new Country[]{countries.get(0), countries.get(70), countries.get(72), countries.get(68), countries.get(73)}),
                new Border(countries.get(72), new Country[]{countries.get(73), countries.get(71), countries.get(78), countries.get(74), countries.get(0)}),
                new Border(countries.get(73), new Country[]{countries.get(68), countries.get(89), countries.get(71), countries.get(72), countries.get(74), countries.get(64), countries.get(90)}),
                new Border(countries.get(74), new Country[]{countries.get(78), countries.get(75), countries.get(77), countries.get(73), countries.get(72), countries.get(90)}),
                new Border(countries.get(75), new Country[]{countries.get(74), countries.get(77), countries.get(76), countries.get(79), countries.get(93), countries.get(90)}),
                new Border(countries.get(76), new Country[]{countries.get(56), countries.get(77), countries.get(75), countries.get(93), countries.get(94)}),
                new Border(countries.get(77), new Country[]{countries.get(76), countries.get(75), countries.get(74), countries.get(94), countries.get(78)}),
                new Border(countries.get(78), new Country[]{countries.get(0), countries.get(74), countries.get(72), countries.get(77), countries.get(94)}),
                new Border(countries.get(79), new Country[]{countries.get(75), countries.get(90), countries.get(93)}),
                new Border(countries.get(80), new Country[]{countries.get(4), countries.get(2), countries.get(1), countries.get(6), countries.get(0), countries.get(3), countries.get(81), countries.get(83)}),
                new Border(countries.get(81), new Country[]{countries.get(22), countries.get(4), countries.get(16), countries.get(17), countries.get(5), countries.get(6), countries.get(83), countries.get(80), countries.get(7), countries.get(82)}),
                new Border(countries.get(82), new Country[]{countries.get(18), countries.get(8), countries.get(17), countries.get(7), countries.get(81), countries.get(10)}),
                new Border(countries.get(83), new Country[]{countries.get(22), countries.get(24), countries.get(2), countries.get(80), countries.get(5), countries.get(23), countries.get(3), countries.get(85), countries.get(84), countries.get(81)}),
                new Border(countries.get(84), new Country[]{countries.get(85), countries.get(86), countries.get(2), countries.get(29), countries.get(0), countries.get(30), countries.get(83)}),
                new Border(countries.get(85), new Country[]{countries.get(28), countries.get(24), countries.get(27), countries.get(29), countries.get(84), countries.get(83)}),
                new Border(countries.get(86), new Country[]{countries.get(69), countries.get(66), countries.get(0), countries.get(30), countries.get(31), countries.get(87), countries.get(84)}),
                new Border(countries.get(87), new Country[]{countries.get(67), countries.get(66), countries.get(65), countries.get(88), countries.get(31), countries.get(32), countries.get(86), countries.get(89)}),
                new Border(countries.get(88), new Country[]{countries.get(33), countries.get(26), countries.get(58), countries.get(32), countries.get(87), countries.get(89)}),
                new Border(countries.get(89), new Country[]{countries.get(67), countries.get(73), countries.get(63), countries.get(58), countries.get(61), countries.get(65), countries.get(68), countries.get(64), countries.get(60), countries.get(90), countries.get(88), countries.get(87)}),
                new Border(countries.get(90), new Country[]{countries.get(62), countries.get(89), countries.get(91), countries.get(73), countries.get(79), countries.get(63), countries.get(74), countries.get(64), countries.get(75), countries.get(59), countries.get(92), countries.get(93)}),
                new Border(countries.get(91), new Country[]{countries.get(44), countries.get(41), countries.get(50), countries.get(59), countries.get(92), countries.get(90)}),
                new Border(countries.get(92), new Country[]{countries.get(96), countries.get(91), countries.get(48), countries.get(50), countries.get(51), countries.get(54), countries.get(93), countries.get(90)}),
                new Border(countries.get(93), new Country[]{countries.get(76), countries.get(90), countries.get(92), countries.get(94), countries.get(79), countries.get(56), countries.get(54), countries.get(75)}),
                new Border(countries.get(94), new Country[]{countries.get(76), countries.get(56), countries.get(57), countries.get(77), countries.get(0), countries.get(93), countries.get(78)}),
                new Border(countries.get(95), new Country[]{countries.get(46), countries.get(42), countries.get(15), countries.get(47), countries.get(96), countries.get(97)}),
                new Border(countries.get(96), new Country[]{countries.get(48), countries.get(51), countries.get(47), countries.get(92), countries.get(97), countries.get(95)}),
                new Border(countries.get(97), new Country[]{countries.get(38), countries.get(15), countries.get(96), countries.get(51), countries.get(95), countries.get(53), countries.get(0), countries.get(35), countries.get(39), countries.get(52)})};

        for (int i = 0; i < tempBorders.length; i++) {
            countries.get(i).setBorders(tempBorders[i]);
        }

        return countries;
    }

    private static void setOccupiedBy(ArrayList<Country> countries) {
        countries.get(0).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(1).setOccupiedBy(Team.BRITAIN, UnitType.NAVY);
        countries.get(2).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(3).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(4).setOccupiedBy(Team.BRITAIN, UnitType.ARMY);
        countries.get(5).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(6).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(7).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(8).setOccupiedBy(Team.BELARUS, UnitType.ARMY);
        countries.get(9).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(10).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(11).setOccupiedBy(Team.BELARUS, UnitType.ARMY);
        countries.get(12).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(13).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(14).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(15).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(16).setOccupiedBy(Team.GERMANY, UnitType.NAVY);
        countries.get(17).setOccupiedBy(Team.GERMANY, UnitType.ARMY);
        countries.get(18).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(19).setOccupiedBy(Team.GERMANY, UnitType.ARMY);
        countries.get(20).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(21).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(22).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(23).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(24).setOccupiedBy(Team.FRANCE, UnitType.NAVY);
        countries.get(25).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(26).setOccupiedBy(Team.FRANCE, UnitType.ARMY);
        countries.get(27).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(28).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(29).setOccupiedBy(Team.SPAIN, UnitType.ARMY);
        countries.get(30).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(31).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(32).setOccupiedBy(Team.SPAIN, UnitType.NAVY);
        countries.get(33).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(34).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(35).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(36).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(37).setOccupiedBy(Team.RUSSIA, UnitType.ARMY);
        countries.get(38).setOccupiedBy(Team.RUSSIA, UnitType.NAVY);
        countries.get(39).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(40).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(41).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(42).setOccupiedBy(Team.AUSTRIA, UnitType.ARMY);
        countries.get(43).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(44).setOccupiedBy(Team.AUSTRIA, UnitType.ARMY);
        countries.get(45).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(46).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(47).setOccupiedBy(Team.BALKANS, UnitType.ARMY);
        countries.get(48).setOccupiedBy(Team.BALKANS, UnitType.NAVY);
        countries.get(49).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(50).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(51).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(52).setOccupiedBy(Team.OTTOMAN, UnitType.NAVY);
        countries.get(53).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(54).setOccupiedBy(Team.OTTOMAN, UnitType.NAVY);
        countries.get(55).setOccupiedBy(Team.OTTOMAN, UnitType.ARMY);
        countries.get(56).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(57).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(58).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(59).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(60).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(61).setOccupiedBy(Team.ITALY, UnitType.NAVY);
        countries.get(62).setOccupiedBy(Team.ITALY, UnitType.ARMY);
        countries.get(63).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(64).setOccupiedBy(Team.ITALY, UnitType.ARMY);
        countries.get(65).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(66).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(67).setOccupiedBy(Team.NORTH_AFRICA, UnitType.NAVY);
        countries.get(68).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(69).setOccupiedBy(Team.NORTH_AFRICA, UnitType.ARMY);
        countries.get(70).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(71).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(72).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(73).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(74).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(75).setOccupiedBy(Team.EGYPT, UnitType.NAVY);
        countries.get(76).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(77).setOccupiedBy(Team.EGYPT, UnitType.NAVY);
        countries.get(78).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(79).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(80).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(81).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(82).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(83).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(84).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(85).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(86).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(87).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(88).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(89).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(90).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(91).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(92).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(93).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(94).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(95).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(96).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(97).setOccupiedBy(Team.NULL, UnitType.EMPTY);
    }
}
