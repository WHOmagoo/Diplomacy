import javax.swing.*;

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
                    new ImageIcon("Files\\Austriaarmy.png"),
                    new ImageIcon("Files\\Austriaarmyrollover.png"),
                    new ImageIcon("Files\\Austriaarmyclick.png"),
                    new ImageIcon("Files\\Austrianavy.png"),
                    new ImageIcon("Files\\Austrianavyrollover.png"),
                    new ImageIcon("Files\\Austrianavyclick.png")
            }),

    BALKANS(new ImageIcon[]
            {
                    new ImageIcon("Files\\Balkansarmy.png"),
                    new ImageIcon("Files\\Balkansarmyrollover.png"),
                    new ImageIcon("Files\\Balkansarmyclick.png"),
                    new ImageIcon("Files\\Balkansnavy.png"),
                    new ImageIcon("Files\\Balkansnavyrollover.png"),
                    new ImageIcon("Files\\Balkansnavyclick.png")
            }),

    BELARUS(new ImageIcon[]
            {
                    new ImageIcon("Files\\Belarusarmy.png"),
                    new ImageIcon("Files\\Belarusarmyrollover.png"),
                    new ImageIcon("Files\\Belarusarmyclick.png"),
                    new ImageIcon("Files\\Belarusnavy.png"),
                    new ImageIcon("Files\\Belarusnavyrollover.png"),
                    new ImageIcon("Files\\Belarusnavyclick.png")
            }),

    BRITAIN(new ImageIcon[]
            {
                    new ImageIcon("Files\\Star.png"),
                    new ImageIcon("Files\\rolloverstar.png"),
                    new ImageIcon("Files\\clickstar.png"),
                    new ImageIcon("Files\\anchor.png"),
                    new ImageIcon("Files\\rolloveranchor.png"),
                    new ImageIcon("Files\\clickanchor.png")
            }),

    EGYPT(new ImageIcon[]
            {
                    new ImageIcon("Files\\Egyptarmy.png"),
                    new ImageIcon("Files\\Egyptarmyrollover.png"),
                    new ImageIcon("Files\\Egyptarmyclick.png"),
                    new ImageIcon("Files\\Egyptnavy.png"),
                    new ImageIcon("Files\\Egyptnavyrollover.png"),
                    new ImageIcon("Files\\Egyptnavyclick.png")
            }),

    FRANCE(new ImageIcon[]
            {
                    new ImageIcon("Files\\Francearmy.png"),
                    new ImageIcon("Files\\Francearmyrollover.png"),
                    new ImageIcon("Files\\Francearmyclick.png"),
                    new ImageIcon("Files\\Francenavy.png"),
                    new ImageIcon("Files\\Francenavyrollover.png"),
                    new ImageIcon("Files\\Francenavyclick.png")
            }),

    GERMANY(new ImageIcon[]
            {
                    new ImageIcon("Files\\Germanyarmy.png"),
                    new ImageIcon("Files\\Germanyarmyrollover.png"),
                    new ImageIcon("Files\\Germanyarmyclick.png"),
                    new ImageIcon("Files\\Germanynavy.png"),
                    new ImageIcon("Files\\Germanynavyrollover.png"),
                    new ImageIcon("Files\\Germanynavyclick.png")
            }),

    ITALY(new ImageIcon[]
            {
                    new ImageIcon("Files\\Italyarmy.png"),
                    new ImageIcon("Files\\Italyarmyrollover.png"),
                    new ImageIcon("Files\\Italyarmyclick.png"),
                    new ImageIcon("Files\\Italynavy.png"),
                    new ImageIcon("Files\\Italynavyrollover.png"),
                    new ImageIcon("Files\\Italynavyclick.png")
            }),

    NORTH_AFRICA(new ImageIcon[]
            {
                    new ImageIcon("Files\\NAfricaarmy.png"),
                    new ImageIcon("Files\\NAfricaarmyrollover.png"),
                    new ImageIcon("Files\\NAfricaarmyclick.png"),
                    new ImageIcon("Files\\NAfricanavy.png"),
                    new ImageIcon("Files\\NAfricanavyrollover.png"),
                    new ImageIcon("Files\\NAfricanavyclick.png")
            }),

    OTTOMAN(new ImageIcon[]
            {
                    new ImageIcon("Files\\Ottomanarmy.png"),
                    new ImageIcon("Files\\Ottomanarmyrollover.png"),
                    new ImageIcon("Files\\Ottomanarmyclick.png"),
                    new ImageIcon("Files\\Ottomannavy.png"),
                    new ImageIcon("Files\\Ottomannavyrollover.png"),
                    new ImageIcon("Files\\Ottomannavyclick.png")
            }),

    RUSSIA(new ImageIcon[]
            {
                    new ImageIcon("Files\\Russiaarmy.png"),
                    new ImageIcon("Files\\Russiaarmyrollover.png"),
                    new ImageIcon("Files\\Russiaarmyclick.png"),
                    new ImageIcon("Files\\Russianavy.png"),
                    new ImageIcon("Files\\Russianavyrollover.png"),
                    new ImageIcon("Files\\Russianavyclick.png")
            }),

    SPAIN(new ImageIcon[]
            {
                    new ImageIcon("Files\\Spainarmy.png"),
                    new ImageIcon("Files\\Spainarmyrollover.png"),
                    new ImageIcon("Files\\Spainarmyclick.png"),
                    new ImageIcon("Files\\Spainnavy.png"),
                    new ImageIcon("Files\\Spainnavyrollover.png"),
                    new ImageIcon("Files\\Spainnavyclick.png")
            });



    private ImageIcon[] icons;

    Team(ImageIcon[] i) {
        icons = i;
    }

    ImageIcon[] getIcons() {
        return icons;
    }

    ImageIcon getArmyIcon() {
        return icons[0];
    }

    ImageIcon getArmyRolloverIcon() {
        return icons[1];
    }

    ImageIcon getArmyClickIcon() {
        return icons[2];
    }

    ImageIcon getNavyIcon() {
        return icons[3];
    }

    ImageIcon getNavyRolloverIcon() {
        return icons[4];
    }

    ImageIcon getNavyClickIcon() {
        return icons[5];
    }

    ImageIcon getIcon(UnitType unitType) {
        if (unitType == UnitType.ARMY) {
            return icons[0];
        } else if (unitType == UnitType.NAVY) {
            return icons[3];
        } else {
            return null;
        }
    }

    ImageIcon getRolloverIcon(UnitType unitType) {
        if (unitType == UnitType.ARMY) {
            return icons[1];
        } else if (unitType == UnitType.NAVY) {
            return icons[4];
        } else {
            return null;
        }
    }

    ImageIcon getPressedIcon(UnitType unitType) {
        if (unitType == UnitType.ARMY) {
            return icons[2];
        } else if (unitType == UnitType.NAVY) {
            return icons[5];
        } else {
            return null;
        }
    }

}
