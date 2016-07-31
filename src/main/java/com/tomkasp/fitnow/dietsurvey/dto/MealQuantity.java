package com.tomkasp.fitnow.dietsurvey.dto;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
public class MealQuantity {
    Meal monday;
    Meal tueasday;
    Meal wednesday;
    Meal thrusday;
    Meal firday;
    Meal saturday;
    Meal sunday;

    public Meal getFirday() {
        return firday;
    }

    public MealQuantity setFirday(Meal firday) {
        this.firday = firday;
        return this;
    }

    public Meal getMonday() {
        return monday;
    }

    public MealQuantity setMonday(Meal monday) {
        this.monday = monday;
        return this;
    }

    public Meal getSaturday() {
        return saturday;
    }

    public MealQuantity setSaturday(Meal saturday) {
        this.saturday = saturday;
        return this;
    }

    public Meal getSunday() {
        return sunday;
    }

    public MealQuantity setSunday(Meal sunday) {
        this.sunday = sunday;
        return this;
    }

    public Meal getThrusday() {
        return thrusday;
    }

    public MealQuantity setThrusday(Meal thrusday) {
        this.thrusday = thrusday;
        return this;
    }

    public Meal getTueasday() {
        return tueasday;
    }

    public MealQuantity setTueasday(Meal tueasday) {
        this.tueasday = tueasday;
        return this;
    }

    public Meal getWednesday() {
        return wednesday;
    }

    public MealQuantity setWednesday(Meal wednesday) {
        this.wednesday = wednesday;
        return this;
    }

    public static final class Meal {
        Boolean breakfast;
        Boolean secbreakfast;
        Boolean lunch;
        Boolean dinner;
        Boolean supper;

        public Boolean getBreakfast() {
            return breakfast;
        }

        public Meal setBreakfast(Boolean breakfast) {
            this.breakfast = breakfast;
            return this;
        }

        public Boolean getDinner() {
            return dinner;
        }

        public Meal setDinner(Boolean dinner) {
            this.dinner = dinner;
            return this;
        }

        public Boolean getLunch() {
            return lunch;
        }

        public Meal setLunch(Boolean lunch) {
            this.lunch = lunch;
            return this;
        }

        public Boolean getSecbreakfast() {
            return secbreakfast;
        }

        public Meal setSecbreakfast(Boolean secbreakfast) {
            this.secbreakfast = secbreakfast;
            return this;
        }

        public Boolean getSupper() {
            return supper;
        }

        public Meal setSupper(Boolean supper) {
            this.supper = supper;
            return this;
        }
    }
}

