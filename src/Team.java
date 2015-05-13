import javax.swing.*;

public enum Team {
    NULL(new ImageIcon[]{
    }),

    BRITAIN(new ImageIcon[]
            {
                    new ImageIcon("Star.png"),
                    new ImageIcon("rolloverstar.png"),
                    new ImageIcon("clickstar.png"),
                    new ImageIcon("anchor.png"),
                    new ImageIcon("rolloveranchor.png"),
                    new ImageIcon("clickanchor.png")
            }),

    AUSTRIA(new ImageIcon[]
            {
                    new ImageIcon("Austriaarmy.png"),
                    new ImageIcon("Austriaarmyrollover.png"),
                    new ImageIcon("Austriaarmyclick.png"),
                    new ImageIcon("Austrianavy.png"),
                    new ImageIcon("Austrianavyrollover.png"),
                    new ImageIcon("Austrianavyclick.png")
            }),

    EGYPT(new ImageIcon[]
            {
                    new ImageIcon("Egyptarmy.png"),
                    new ImageIcon("Egyptarmyrollover.png"),
                    new ImageIcon("Egyptarmyclick.png"),
                    new ImageIcon("Egyptnavy.png"),
                    new ImageIcon("Egyptnavyrollover.png"),
                    new ImageIcon("Egyptnavyclick.png")
            }),

    FRANCE(new ImageIcon[]
            {

                    new ImageIcon("Francearmy.png"),
                    new ImageIcon("Francearmyrollover.png"),
                    new ImageIcon("Francearmyclick.png"),
                    new ImageIcon("Francenavy.png"),
                    new ImageIcon("Francenavyrollover.png"),
                    new ImageIcon("Francenavyclick.png")
            }),

    GERMANY(new ImageIcon[]
            {
                    new ImageIcon("Germanyarmy.png"),
                    new ImageIcon("Germanyarmyrollover.png"),
                    new ImageIcon("Germanyarmyclick.png"),
                    new ImageIcon("Germanynavy.png"),
                    new ImageIcon("Germanynavyrollover.png"),
                    new ImageIcon("Germanynavyclick.png")
            }),

    ITALY(new ImageIcon[]
            {
                    new ImageIcon("Italyarmy.png"),
                    new ImageIcon("Italyarmyrollover.png"),
                    new ImageIcon("Italyarmyclick.png"),
                    new ImageIcon("Italynavy.png"),
                    new ImageIcon("Italynavyrollover.png"),
                    new ImageIcon("Italynavyclick.png")
            }),

    NORTH_AFRICA(new ImageIcon[]
            {
                    new ImageIcon("NAfricaarmy.png"),
                    new ImageIcon("NAfricaarmyrollover.png"),
                    new ImageIcon("NAfricaarmyclick.png"),
                    new ImageIcon("NAfricanavy.png"),
                    new ImageIcon("NAfricanavyrollover.png"),
                    new ImageIcon("NAfricanavyclick.png")
            }),

    OTTOMAN(new ImageIcon[]
            {
                    new ImageIcon("Ottomanarmy.png"),
                    new ImageIcon("Ottomanarmyrollover.png"),
                    new ImageIcon("Ottomanarmyclick.png"),
                    new ImageIcon("Ottomannavy.png"),
                    new ImageIcon("Ottomannavyrollover.png"),
                    new ImageIcon("Ottomannavyclick.png")
            }),

    RUSSIA(new ImageIcon[]
            {
                    new ImageIcon("Russiaarmy.png"),
                    new ImageIcon("Russiaarmyrollover.png"),
                    new ImageIcon("Russiaarmyclick.png"),
                    new ImageIcon("Russianavy.png"),
                    new ImageIcon("Russianavyrollover.png"),
                    new ImageIcon("Russianavyclick.png")
            }),

    SPAIN(new ImageIcon[]
            {
                    new ImageIcon("Spainarmy.png"),
                    new ImageIcon("Spainarmyrollover.png"),
                    new ImageIcon("Spainarmyclick.png"),
                    new ImageIcon("Spainnavy.png"),
                    new ImageIcon("Spainnavyrollover.png"),
                    new ImageIcon("Spainnavyclick.png")
            }),

    BALKANS(new ImageIcon[]
            {
                    new ImageIcon("Balkansarmy.png"),
                    new ImageIcon("Balkansarmyrollover.png"),
                    new ImageIcon("Balkansarmyclick.png"),
                    new ImageIcon("Balkansnavy.png"),
                    new ImageIcon("Balkansnavyrollover.png"),
                    new ImageIcon("Balkansnavyclick.png")
            }),

    BELARUS(new ImageIcon[]{
            new ImageIcon("Belarusarmy.png"),
            new ImageIcon("Belarusarmyrollover.png"),
            new ImageIcon("Belarusarmyclick.png"),
            new ImageIcon("Belarusnavy.png"),
            new ImageIcon("Belarusnavyrollover.png"),
            new ImageIcon("Belarusnavyclick.png")
    });

    private ImageIcon[] icons;

    Team(ImageIcon[] i) {
        icons = i;
    }

    Icon[] getIcons() {
        return icons;
    }

    Icon getArmyIcon() {
        return icons[0];
    }

    Icon getArmyRolloverIcon() {
        return icons[1];
    }

    Icon getArmyClickIcon() {
        return icons[2];
    }

    Icon getNavyIcon() {
        return icons[3];
    }

    Icon getNavyRolloverIcon() {
        return icons[4];
    }

    Icon getNavyClickIcon() {
        return icons[5];
    }

}
