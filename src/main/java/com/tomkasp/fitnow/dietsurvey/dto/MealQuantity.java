package com.tomkasp.fitnow.dietsurvey.dto;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
public class MealQuantity {
    private Meal monday = new Meal();
    private Meal tuesday = new Meal();
    private Meal wednesday = new Meal();
    private Meal thursday = new Meal();
    private Meal friday = new Meal();
    private Meal saturday = new Meal();
    private Meal sunday = new Meal();

    public Meal getFriday() {
        return friday;
    }

    public MealQuantity setFriday(Meal friday) {
        this.friday = friday;
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

    public Meal getThursday() {
        return thursday;
    }

    public MealQuantity setThursday(Meal thursday) {
        this.thursday = thursday;
        return this;
    }

    public Meal getTuesday() {
        return tuesday;
    }

    public MealQuantity setTuesday(Meal tuesday) {
        this.tuesday = tuesday;
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
        Boolean breakfast = true;
        Boolean secbreakfast = true;
        Boolean lunch = true;
        Boolean dinner = true;
        Boolean supper = true;

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

