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
        map.updateGraphics();
        map.addAllCountries();
        return map;
    }

    private static ArrayList<Country> createCountries() {
        final String[] COUNTRY_NAME_LIST = {
/*0-4*/     "Off Map", "Liverpool", "Ireland", "Wales", "Edinburgh",
/*5-9*/     "London", "Norway", "Sweden", "Finland", "Vologda",
/*10-14*/   "Leningrad", "Smolensk", "Moscow", "Bellorussia", "Presov",
/*15-19*/   "Kiev", "Holland", "Kiel", "Berlin", "Siliesia",
/*20-24*/   "Ruhr", "Munich", "Belgium", "Picardy", "Brest",
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
                new Point(15, 75),      /*Off Map*/
                new Point(202, 103),    /*Liverpool*/
                new Point(83, 213),     /*Ireland*/
                new Point(201, 166),    /*Wales*/
                new Point(249, 89),     /*Edinburgh*/
                new Point(261, 151),    /*London*/
                new Point(368, 48),     /*Norway*/
                new Point(457, 59),     /*Sweden*/
                new Point(576, 72),     /*Finland*/
                new Point(665, 99),     /*Vologda*/
                new Point(584, 122),    /*Leningrad*/
                new Point(660, 158),    /*Smolensk*/
                new Point(727, 167),    /*Moscow*/
                new Point(581, 171),    /*Bellorussia*/
                new Point(584, 221),    /*Presov*/
                new Point(643, 218),    /*Kiev*/
                new Point(367, 182),    /*Holland*/
                new Point(423, 167),    /*Kiel*/
                new Point(483, 166),    /*Berlin*/
                new Point(487, 206),    /*Siliesia*/
                new Point(389, 239),    /*Rurh*/
                new Point(435, 255),    /*Munich*/
                new Point(325, 232),    /*Belgium*/
                new Point(280, 262),    /*Picardy*/
                new Point(217, 280),    /*Brest*/
                new Point(295, 324),    /*Paris*/
                new Point(289, 376),    /*Marseille*/
                new Point(226, 351),    /*Gacony*/
                new Point(173, 383),    /*Bilboa*/
                new Point(127, 412),    /*Lugo*/
                new Point(77, 449),     /*Portugal*/
                new Point(125, 500),    /*Seville*/
                new Point(182, 464),    /*Valancia*/
                new Point(237, 414),    /*Barcelona*/
                new Point(741, 83),     /*Ust*/
                new Point(893, 68),     /*Pechora*/
                new Point(821, 106),    /*Komi*/
                new Point(823, 153),    /*Gorki*/
                new Point(764, 220),    /*Crimea*/
                new Point(868, 216),    /*Rostov*/
                new Point(497, 248),    /*Czechoslovakia*/
                new Point(471, 294),    /*Austria*/
                new Point(577, 270),    /*Hungary*/
                new Point(533, 296),    /*Bosnia*/
                new Point(569, 395),    /*Albania*/
                new Point(581, 348),    /*Serbia*/
                new Point(625, 298),    /*Banat*/
                new Point(679, 307),    /*Romania*/
                new Point(693, 355),    /*Bulgaria*/
                new Point(636, 378),    /*Macedonia*/
                new Point(632, 448),    /*Greece*/
                new Point(764, 372),    /*Istanbul*/
                new Point(841, 349),    /*Samsun*/
                new Point(901, 356),    /*Kras*/
                new Point(763, 426),    /*Izmir*/
                new Point(879, 464),    /*Lebanon*/
                new Point(833, 538),    /*Israel*/
                new Point(867, 592),    /*Jordan*/
                new Point(386, 353),    /*Piedmont*/
                new Point(444, 379),    /*Venice*/
                new Point(406, 398),    /*Tuscany*/
                new Point(449, 429),    /*Rome*/
                new Point(513, 423),    /*Apulia*/
                new Point(509, 468),    /*Naples*/
                new Point(495, 536),    /*Sicily*/
                new Point(344, 468),    /*Sardinia*/
                new Point(117, 597),    /*Casablanca*/
                new Point(241, 565),    /*Algiers*/
                new Point(358, 553),    /*Setif*/
                new Point(185, 635),    /*Aflou*/
                new Point(320, 653),    /*Sahara*/
                new Point(388, 648),    /*Fazzar*/
                new Point(473, 657),    /*Murzq*/
                new Point(446, 598),    /*Bengasi*/
                new Point(544, 612),    /*Sallum*/
                new Point(625, 567),    /*Tobruk*/
                new Point(714, 592),    /*Cairo*/
                new Point(711, 634),    /*Nile*/
                new Point(613, 661),    /*Sawhaj*/
                new Point(710, 516),    /*Crete*/
                new Point(107, 92),     /*Irish Sea*/
                new Point(315, 98),     /*North Sea*/
                new Point(516, 99),     /*Baltic Sea*/
                new Point(211, 229),    /*English  Channel*/
                new Point(79, 295),     /*Atlantic Ocean*/
                new Point(151, 314),    /*Bay of Biscay*/
                new Point(61, 549),     /*Gibraltar*/
                new Point(261, 495),    /*Western Mediterranean*/
                new Point(271, 446),    /*Gulf of Lyons*/
                new Point(416, 475),    /*Tyrrhenian Sea*/
                new Point(562, 488),    /*Ionian Sea*/
                new Point(481, 347),    /*Adriatic Sea*/
                new Point(692, 420),    /*Agean Sea*/
                new Point(800, 474),    /*Eastern Mediterranean*/
                new Point(788, 646),    /*Red Sea*/
                new Point(696, 245),    /*Lake Cherkassy*/
                new Point(782, 313),    /*West Black Sea*/
                new Point(841, 283)};   /*East Black Sea*/

        boolean[] scoresPoints = new boolean[]{
        false,  /*Off Map*/
        true,   /*Liverpool*/
        false,  /*Ireland*/
        true,   /*Wales*/
        true,   /*Edinburgh*/
        true,   /*London*/
        false,  /*Norway*/
        false,  /*Sweden*/
        true,   /*Finland*/
        true,   /*Vologda*/
        true,   /*Leningrad*/
        true,   /*Smolensk*/
        false,  /*Moscow*/
        false,  /*Bellorussia*/
        false,  /*Presov*/
        false,  /*Kiev*/
        true,   /*Holland*/
        true,   /*Kiel*/
        true,   /*Berlin*/
        true,   /*Siliesia*/
        true,   /*Rurh*/
        false,  /*Munich*/
        false,  /*Belgium*/
        true,   /*Picardy*/
        true,   /*Brest*/
        true,   /*Paris*/
        true,   /*Marseille*/
        false,  /*Gacony*/
        true,   /*Bilboa*/
        true,   /*Lugo*/
        false,  /*Portugal*/
        true,   /*Seville*/
        true,   /*Valancia*/
        false,  /*Barcelona*/
        false,  /*Ust*/
        false,  /*Pechora*/
        true,   /*Komi*/
        true,   /*Gorki*/
        true,   /*Crimea*/
        true,   /*Rostov*/
        false,  /*Czechoslovakia*/
        true,   /*Austria*/
        true,   /*Hungary*/
        true,   /*Bosnia*/
        true,   /*Albania*/
        false,  /*Serbia*/
        false,  /*Banat*/
        true,   /*Romania*/
        true,   /*Bulgaria*/
        true,   /*Macedonia*/
        true,   /*Greece*/
        true,   /*Istanbul*/
        true,   /*Samsun*/
        false,  /*Kras*/
        true,   /*Izmir*/
        true,   /*Lebanon*/
        true,   /*Israel*/
        false,  /*Jordan*/
        false,  /*Piedmont*/
        false,  /*Venice*/
        false,  /*Tuscany*/
        true,   /*Rome*/
        true,   /*Apulia*/
        true,   /*Naples*/
        true,   /*Sicily*/
        true,   /*Sardinia*/
        false,  /*Casablanca*/
        true,   /*Algiers*/
        true,   /*Setif*/
        true,   /*Aflou*/
        false,  /*Sahara*/
        false,  /*Fazzar*/
        false,  /*Murzq*/
        false,  /*Bengasi*/
        true,   /*Sallum*/
        true,   /*Tobruk*/
        false,  /*Cairo*/
        true,   /*Nile*/
        false,  /*Sawhaj*/
        true,   /*Crete*/
        false,  /*Irish Sea*/
        false,  /*North Sea*/
        false,  /*Baltic Sea*/
        false,  /*English  Channel*/
        false,  /*Atlantic Ocean*/
        false,  /*Bay of Biscay*/
        false,  /*Gibraltar*/
        false,  /*Western Mediterranean*/
        false,  /*Gulf of Lyons*/
        false,  /*Tyrrhenian Sea*/
        false,  /*Ionian Sea*/
        false,  /*Adriatic Sea*/
        false,  /*Agean Sea*/
        false,  /*Eastern Mediterranean*/
        false,  /*Red Sea*/
        false,  /*Lake Cherkassy*/
        false,  /*West Black Sea*/
        false   /*East Black Sea*/
        };

        ArrayList<Country> tempCountries = new ArrayList<Country>();
        for (int i = 0; i < COUNTRY_NAME_LIST.length; i++) {
            TileType tileType;
            if (i < 80) {
                tileType = TileType.Landlocked;
            } else {
                tileType = TileType.Water;
            }
            if(scoresPoints[i]) {
                tempCountries.add(new ScoringCountry(COUNTRY_NAME_LIST[i], locations[i], tileType));
            } else {
                tempCountries.add(new Country(COUNTRY_NAME_LIST[i], locations[i], tileType));
            }
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
        countries.get(42).setOccupiedBy(Team.BALKANS, UnitType.ARMY);
        countries.get(43).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(44).setOccupiedBy(Team.BALKANS, UnitType.ARMY);
        countries.get(45).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(46).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(47).setOccupiedBy(Team.EASTERN_EUROPE, UnitType.ARMY);
        countries.get(48).setOccupiedBy(Team.EASTERN_EUROPE, UnitType.NAVY);
        countries.get(49).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(50).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(51).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(52).setOccupiedBy(Team.MIDDLE_EAST, UnitType.NAVY);
        countries.get(53).setOccupiedBy(Team.NULL, UnitType.EMPTY);
        countries.get(54).setOccupiedBy(Team.MIDDLE_EAST, UnitType.NAVY);
        countries.get(55).setOccupiedBy(Team.MIDDLE_EAST, UnitType.ARMY);
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