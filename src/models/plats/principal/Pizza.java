package models.plats.principal;


public abstract class Pizza extends PlatPrincipal {

    public abstract Pizza clone();

    public abstract double cost();

    public abstract  String getDescription();

}
