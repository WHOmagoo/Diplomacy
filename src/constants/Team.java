package constants;

import javax.swing.ImageIcon;
import map.UnitType;

public enum Team {
    NULL(new ImageIcon[]{
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
    }),


    EASTERN_EUROPE(new ImageIcon[]
            {
                    new ImageIcon("files\\Austria\\EasterEuropearmy.png"),
                    new ImageIcon("files\\Austria\\EasternEuropearmyrollover.png"),
                    new ImageIcon("files\\Austria\\EasternEuropearmyclick.png"),
                    new ImageIcon("files\\Austria\\EasternEuropenavy.png"),
                    new ImageIcon("files\\Austria\\EasternEuropenavyrollover.png"),
                    new ImageIcon("files\\Austria\\EasternEuropenavyclick.png")
            }),

    BALKANS(new ImageIcon[]
            {
                    new ImageIcon("files\\Balkans\\Balkansarmy.png"),
                    new ImageIcon("files\\Balkans\\Balkansarmyrollover.png"),
                    new ImageIcon("files\\Balkans\\Balkansarmyclick.png"),
                    new ImageIcon("files\\Balkans\\Balkansnavy.png"),
                    new ImageIcon("files\\Balkans\\Balkansnavyrollover.png"),
                    new ImageIcon("files\\Balkans\\Balkansnavyclick.png")
            }),

    BELARUS(new ImageIcon[]
            {
                    new ImageIcon("files\\Belarus\\Belarusarmy.png"),
                    new ImageIcon("files\\Belarus\\Belarusarmyrollover.png"),
                    new ImageIcon("files\\Belarus\\Belarusarmyclick.png"),
                    new ImageIcon("files\\Belarus\\Belarusnavy.png"),
                    new ImageIcon("files\\Belarus\\Belarusnavyrollover.png"),
                    new ImageIcon("files\\Belarus\\Belarusnavyclick.png")
            }),

    BRITAIN(new ImageIcon[]
            {
                    new ImageIcon("files\\Britain\\Star.png"),
                    new ImageIcon("files\\Britain\\rolloverstar.png"),
                    new ImageIcon("files\\Britain\\clickstar.png"),
                    new ImageIcon("files\\Britain\\anchor.png"),
                    new ImageIcon("files\\Britain\\rolloveranchor.png"),
                    new ImageIcon("files\\Britain\\clickanchor.png")
            }),

    EGYPT(new ImageIcon[]
            {
                    new ImageIcon("files\\Egypt\\Egyptarmy.png"),
                    new ImageIcon("files\\Egypt\\Egyptarmyrollover.png"),
                    new ImageIcon("files\\Egypt\\Egyptarmyclick.png"),
                    new ImageIcon("files\\Egypt\\Egyptnavy.png"),
                    new ImageIcon("files\\Egypt\\Egyptnavyrollover.png"),
                    new ImageIcon("files\\Egypt\\Egyptnavyclick.png")
            }),

    FRANCE(new ImageIcon[]
            {
                    new ImageIcon("files\\France\\Francearmy.png"),
                    new ImageIcon("files\\France\\Francearmyrollover.png"),
                    new ImageIcon("files\\France\\Francearmyclick.png"),
                    new ImageIcon("files\\France\\Francenavy.png"),
                    new ImageIcon("files\\France\\Francenavyrollover.png"),
                    new ImageIcon("files\\France\\Francenavyclick.png")
            }),

    GERMANY(new ImageIcon[]
            {
                    new ImageIcon("files\\Germany\\Germanyarmy.png"),
                    new ImageIcon("files\\Germany\\Germanyarmyrollover.png"),
                    new ImageIcon("files\\Germany\\Germanyarmyclick.png"),
                    new ImageIcon("files\\Germany\\Germanynavy.png"),
                    new ImageIcon("files\\Germany\\Germanynavyrollover.png"),
                    new ImageIcon("files\\Germany\\Germanynavyclick.png")
            }),

    ITALY(new ImageIcon[]
            {
                    new ImageIcon("files\\Italy\\Italyarmy.png"),
                    new ImageIcon("files\\Italy\\Italyarmyrollover.png"),
                    new ImageIcon("files\\Italy\\Italyarmyclick.png"),
                    new ImageIcon("files\\Italy\\Italynavy.png"),
                    new ImageIcon("files\\Italy\\Italynavyrollover.png"),
                    new ImageIcon("files\\Italy\\Italynavyclick.png")
            }),

    NORTH_AFRICA(new ImageIcon[]
            {
                    new ImageIcon("files\\North Africa\\NAfricaarmy.png"),
                    new ImageIcon("files\\North Africa\\NAfricaarmyrollover.png"),
                    new ImageIcon("files\\North Africa\\NAfricaarmyclick.png"),
                    new ImageIcon("files\\North Africa\\NAfricanavy.png"),
                    new ImageIcon("files\\North Africa\\NAfricanavyrollover.png"),
                    new ImageIcon("files\\North Africa\\NAfricanavyclick.png")
            }),

    MIDDLE_EAST(new ImageIcon[]
            {
                    new ImageIcon("files\\Ottoman\\MiddleEastarmy.png"),
                    new ImageIcon("files\\Ottoman\\MiddleEastarmyrollover.png"),
                    new ImageIcon("files\\Ottoman\\MiddleEastarmyclick.png"),
                    new ImageIcon("files\\Ottoman\\MiddleEastnavy.png"),
                    new ImageIcon("files\\Ottoman\\MiddleEastnavyrollover.png"),
                    new ImageIcon("files\\Ottoman\\MiddleEastnavyclick.png")
            }),

    RUSSIA(new ImageIcon[]
            {
                    new ImageIcon("files\\Russia\\Russiaarmy.png"),
                    new ImageIcon("files\\Russia\\Russiaarmyrollover.png"),
                    new ImageIcon("files\\Russia\\Russiaarmyclick.png"),
                    new ImageIcon("files\\Russia\\Russianavy.png"),
                    new ImageIcon("files\\Russia\\Russianavyrollover.png"),
                    new ImageIcon("files\\Russia\\Russianavyclick.png")
            }),

    SPAIN(new ImageIcon[]
            {
                    new ImageIcon("files\\Spain\\Spainarmy.png"),
                    new ImageIcon("files\\Spain\\Spainarmyrollover.png"),
                    new ImageIcon("files\\Spain\\Spainarmyclick.png"),
                    new ImageIcon("files\\Spain\\Spainnavy.png"),
                    new ImageIcon("files\\Spain\\Spainnavyrollover.png"),
                    new ImageIcon("files\\Spain\\Spainnavyclick.png")
            });

    private ImageIcon[] icons;

    Team(ImageIcon[] i) {
        icons = i;
    }

    ImageIcon[] getIcons() {
        return icons;
    }

    public ImageIcon getArmyIcon() {
        return icons[0];
    }

    public ImageIcon getArmyRolloverIcon() {
        return icons[1];
    }

    public ImageIcon getArmyClickIcon() {
        return icons[2];
    }

    public ImageIcon getNavyIcon() {
        return icons[3];
    }

    public ImageIcon getNavyRolloverIcon() {
        return icons[4];
    }

    public ImageIcon getNavyClickIcon() {
        return icons[5];
    }

    public ImageIcon getIcon(UnitType unitType) {
        if (unitType == UnitType.ARMY) {
            return icons[0];
        } else if (unitType == UnitType.NAVY) {
            return icons[3];
        } else {
            return null;
        }
    }

    public ImageIcon getRolloverIcon(UnitType unitType) {
        if (unitType == UnitType.ARMY) {
            return icons[1];
        } else if (unitType == UnitType.NAVY) {
            return icons[4];
        } else {
            return null;
        }
    }

    public ImageIcon getPressedIcon(UnitType unitType) {
        if (unitType == UnitType.ARMY) {
            return icons[2];
        } else if (unitType == UnitType.NAVY) {
            return icons[5];
        } else {
            return null;
        }
    }
}
