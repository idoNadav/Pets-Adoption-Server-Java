package com.petsadoption.common;

public enum PetCategory {
    DOGS,
    CATS,
    FISH,
    SNAKES,
    HORSE,
    PARROTS,
    OWLS,
    BIRDS,
    RABBITS,
    SQUIRRELS,
    LIZARDS,
    IGUANAS,
    HEDGEHOGS,
    BATS,
    TURTLES,
    SHEEP,
    FERRETS,
    DONKEY,
    SPIDERS;

    public static PetCategory getPetCategory(String category) {
        for (PetCategory p : PetCategory.values()) {
            if (p.name().equals(category))
                return p;
        }
        return null;
    }
}