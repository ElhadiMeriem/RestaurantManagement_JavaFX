package models.plats.principal;

public class ItalianFactory implements PlatPrincipalFactory {

    private static ItalianFactory instance;

    private ItalianFactory() {

    }

    public static ItalianFactory getInstance() {

        if (instance == null)
            instance = new ItalianFactory();

        return instance;
    }

    @Override
    public Pizza createPizza(PizzaType type) {

        switch (type) {
            case FORMAGGIO:
                return new Formaggio();
            case FRUTTI_DI_MARE:
                return new FruttiDiMare();
        }

        return null;

    }

}
