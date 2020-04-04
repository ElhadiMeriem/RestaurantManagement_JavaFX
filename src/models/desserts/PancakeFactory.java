package models.desserts;

public class PancakeFactory {

    private static PancakeFactory instance;

    private PancakeFactory() {

    }

    public static PancakeFactory getInstance() {

        if (instance == null)
            instance = new PancakeFactory();

        return instance;
    }

    public Pancake makePancake(PancakeType type) {

        switch (type) {
            case CARRE:
                return new SquarePancake();
            case CIRCULAIRE:
                return new CirclePancake();
        }

        return null;
    }

}
