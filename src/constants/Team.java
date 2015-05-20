package constants;

import map.UnitType;

import javax.swing.ImageIcon;

public enum Team {
    NULL(new ImageIcon[]{
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
            new ImageIcon(),
    }),


    AUSTRIA(new ImageIcon[]
            {
                    new ImageIcon("Files\\Austria\\Austriaarmy.png"),
                    new ImageIcon("Files\\Austria\\Austriaarmyrollover.png"),
                    new ImageIcon("Files\\Austria\\Austriaarmyclick.png"),
                    new ImageIcon("Files\\Austria\\Austrianavy.png"),
                    new ImageIcon("Files\\Austria\\Austrianavyrollover.png"),
                    new ImageIcon("Files\\Austria\\Austrianavyclick.png")
            }),

    BALKANS(new ImageIcon[]
            {
                    new ImageIcon("Files\\Balkans\\Balkansarmy.png"),
                    new ImageIcon("Files\\Balkans\\Balkansarmyrollover.png"),
                    new ImageIcon("Files\\Balkans\\Balkansarmyclick.png"),
                    new ImageIcon("Files\\Balkans\\Balkansnavy.png"),
                    new ImageIcon("Files\\Balkans\\Balkansnavyrollover.png"),
                    new ImageIcon("Files\\Balkans\\Balkansnavyclick.png")
            }),

    BELARUS(new ImageIcon[]
            {
                    new ImageIcon("Files\\Belarus\\Belarusarmy.png"),
                    new ImageIcon("Files\\Belarus\\Belarusarmyrollover.png"),
                    new ImageIcon("Files\\Belarus\\Belarusarmyclick.png"),
                    new ImageIcon("Files\\Belarus\\Belarusnavy.png"),
                    new ImageIcon("Files\\Belarus\\Belarusnavyrollover.png"),
                    new ImageIcon("Files\\Belarus\\Belarusnavyclick.png")
            }),

    BRITAIN(new ImageIcon[]
            {
                    new ImageIcon("Files\\Britain\\Star.png"),
                    new ImageIcon("Files\\Britain\\rolloverstar.png"),
                    new ImageIcon("Files\\Britain\\clickstar.png"),
                    new ImageIcon("Files\\Britain\\anchor.png"),
                    new ImageIcon("Files\\Britain\\rolloveranchor.png"),
                    new ImageIcon("Files\\Britain\\clickanchor.png")
            }),

    EGYPT(new ImageIcon[]
            {
                    new ImageIcon("Files\\Egypt\\Egyptarmy.png"),
                    new ImageIcon("Files\\Egypt\\Egyptarmyrollover.png"),
                    new ImageIcon("Files\\Egypt\\Egyptarmyclick.png"),
                    new ImageIcon("Files\\Egypt\\Egyptnavy.png"),
                    new ImageIcon("Files\\Egypt\\Egyptnavyrollover.png"),
                    new ImageIcon("Files\\Egypt\\Egyptnavyclick.png")
            }),

    FRANCE(new ImageIcon[]
            {
                    new ImageIcon("Files\\France\\Francearmy.png"),
                    new ImageIcon("Files\\France\\Francearmyrollover.png"),
                    new ImageIcon("Files\\France\\Francearmyclick.png"),
                    new ImageIcon("Files\\France\\Francenavy.png"),
                    new ImageIcon("Files\\France\\Francenavyrollover.png"),
                    new ImageIcon("Files\\France\\Francenavyclick.png")
            }),

    GERMANY(new ImageIcon[]
            {
                    new ImageIcon("Files\\Germany\\Germanyarmy.png"),
                    new ImageIcon("Files\\Germany\\Germanyarmyrollover.png"),
                    new ImageIcon("Files\\Germany\\Germanyarmyclick.png"),
                    new ImageIcon("Files\\Germany\\Germanynavy.png"),
                    new ImageIcon("Files\\Germany\\Germanynavyrollover.png"),
                    new ImageIcon("Files\\Germany\\Germanynavyclick.png")
            }),

    ITALY(new ImageIcon[]
            {
                    new ImageIcon("Files\\Italy\\Italyarmy.png"),
                    new ImageIcon("Files\\Italy\\Italyarmyrollover.png"),
                    new ImageIcon("Files\\Italy\\Italyarmyclick.png"),
                    new ImageIcon("Files\\Italy\\Italynavy.png"),
                    new ImageIcon("Files\\Italy\\Italynavyrollover.png"),
                    new ImageIcon("Files\\Italy\\Italynavyclick.png")
            }),

    NORTH_AFRICA(new ImageIcon[]
            {
                    new ImageIcon("Files\\North Africa\\NAfricaarmy.png"),
                    new ImageIcon("Files\\North Africa\\NAfricaarmyrollover.png"),
                    new ImageIcon("Files\\North Africa\\NAfricaarmyclick.png"),
                    new ImageIcon("Files\\North Africa\\NAfricanavy.png"),
                    new ImageIcon("Files\\North Africa\\NAfricanavyrollover.png"),
                    new ImageIcon("Files\\North Africa\\NAfricanavyclick.png")
            }),

    OTTOMAN(new ImageIcon[]
            {
                    new ImageIcon("Files\\Ottoman\\Ottomanarmy.png"),
                    new ImageIcon("Files\\Ottoman\\Ottomanarmyrollover.png"),
                    new ImageIcon("Files\\Ottoman\\Ottomanarmyclick.png"),
                    new ImageIcon("Files\\Ottoman\\Ottomannavy.png"),
                    new ImageIcon("Files\\Ottoman\\Ottomannavyrollover.png"),
                    new ImageIcon("Files\\Ottoman\\Ottomannavyclick.png")
            }),

    RUSSIA(new ImageIcon[]
            {
                    new ImageIcon("Files\\Russia\\Russiaarmy.png"),
                    new ImageIcon("Files\\Russia\\Russiaarmyrollover.png"),
                    new ImageIcon("Files\\Russia\\Russiaarmyclick.png"),
                    new ImageIcon("Files\\Russia\\Russianavy.png"),
                    new ImageIcon("Files\\Russia\\Russianavyrollover.png"),
                    new ImageIcon("Files\\Russia\\Russianavyclick.png")
            }),

    SPAIN(new ImageIcon[]
            {
                    new ImageIcon("Files\\Spain\\Spainarmy.png"),
                    new ImageIcon("Files\\Spain\\Spainarmyrollover.png"),
                    new ImageIcon("Files\\Spain\\Spainarmyclick.png"),
                    new ImageIcon("Files\\Spain\\Spainnavy.png"),
                    new ImageIcon("Files\\Spain\\Spainnavyrollover.png"),
                    new ImageIcon("Files\\Spain\\Spainnavyclick.png")
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
