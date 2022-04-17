package enums;

/**
 * @author Artyom
 * @version 2.0
 */
public class EnumSwitch {
    //test
    public static final double finalBidNoMagic_0_1 = 0.1; //2
    public static final double finalBidNoMagic_0_12 = 0.12; //1
    public static final double finalBidNoMagic_0_11 = 0.11; //2
    public static final double finalBidNoMagic_0_15 = 0.15; //1
    public static final double finalBidNoMagic_0_165 = 0.165; //1
    public static final double finalBidNoMagic_0_18 = 0.18; //1
    public static final double finalBidNoMagic_0_198 = 0.198; //1
    public static final double finalBidNoMagic_0_2 = 0.2; //2
    public static final double finalBidNoMagic_0_216 = 0.216; //1
    public static final double finalBidNoMagic_0_22 = 0.22; //1
    public static final double finalBidNoMagic_0_24 = 0.24; //1
    public static final double finalBidNoMagic_0_3 = 0.3; //3
    public static final double finalBidNoMagic_0_5 = 0.5; //1
    public static final double finalBidNoMagic_0_7 = 0.7; //1
    public static final double finalBidNoMagic_2 = 2; //8

    public static final double condition_4000000 = 4000000; //4
    public static final double condition_7000000 = 7000000; //4
    public static final double condition_10000000 = 10000000; //6
    public static final double condition_300000000 = 300000000; //10
    public static final double condition_20000000 = 20000000; //2
    public static final double condition_50000000 = 50000000; //2


    //test

    // Объявление числовой переменной, хранящей значение налоговой ставки
    public static double finalBid;

    // Объявление переменной для хранения значения кадастровой стоимости объекта налогообложения
    public static double cadastralValue;

    public void setCadastralValue(double cadastralValue) {
        this.cadastralValue = cadastralValue;
    }

    public double getFinalBid() {
        return finalBid;
    }

    /**
     * //Поменять местами константы
     * FIXME убрать Магические числа
     */
    public static void enumUse(int regionIndex, int propertyIndex) {
        //FIXME Вынести в сервлет чтобы суммировало
        int sum = propertyIndex + regionIndex;
        creat(ReaderCoff.getMassCoff().get(sum));
    }

    public static void creat(int ordinal) {
        Selection day;
        day = cityProperty.values()[ordinal];
        finalBid = day.changeFinalBid();
    }

    /**
     * Вложенный класс перечислений cityProperty, реализовывающий интерфейс Selection
     */
    enum cityProperty implements Selection {
        /**
         * Объеденины Ufa - Room + Apartment
         */
        UfaRoom_UfaApartment() { //0-1

            @Override
            public double changeFinalBid() {
                System.out.println("UfaRoom_UfaApartment");
                //FIXME 100000000 ---> 10000000 УБРАЛ  ОДИН 0 для бывшего UfaRoom
                if (cadastralValue <= condition_4000000) {
                    return finalBidNoMagic_0_11;
                } else if ((cadastralValue <= condition_7000000) & (condition_4000000 < cadastralValue)) {
                    return finalBidNoMagic_0_165;
                } else if ((cadastralValue <= condition_10000000) & (condition_7000000 < cadastralValue)) {
                    return finalBidNoMagic_0_198;
                } else if (cadastralValue > condition_10000000) {
                    return finalBidNoMagic_0_22;
                    //TODO что это
                } else if (cadastralValue > condition_300000000) {
                }
                return finalBidNoMagic_2;
            }
        },
        UfaHouse { //2

            @Override
            public double changeFinalBid() {
                System.out.println("UfaHouse");
                if (cadastralValue <= condition_4000000) {
                    return finalBidNoMagic_0_12;
                } else if ((cadastralValue <= condition_7000000) & (condition_4000000 < cadastralValue)) {
                    return finalBidNoMagic_0_18;
                } else if ((cadastralValue <= condition_10000000) & (condition_7000000 < cadastralValue)) {
                    return finalBidNoMagic_0_216;
                } else if (cadastralValue > condition_10000000) {
                    return finalBidNoMagic_0_24;
                } else if (cadastralValue > condition_300000000) {
                }
                return finalBidNoMagic_2;
            }
        },
        UfaCar { //3

            @Override
            public double changeFinalBid() {
                System.out.println("UfaCar");
                if (cadastralValue > condition_300000000) {
                    return finalBidNoMagic_2;
                } else
                    return finalBidNoMagic_0_11;
            }
        },

        /**
         * Объеденины Others - Ufa+Kazan+Moscow
         */
        UfaOthers_KazanOthers_MoscowOthers { //4 8 13

            @Override
            public double changeFinalBid() {
                System.out.println("UfaOthers_KazanOthers_MoscowOthers");
                if (cadastralValue > condition_300000000) {
                    return finalBidNoMagic_2;
                } else
                    return finalBidNoMagic_0_5;
            }
        },
        /**
         * Объеденины Kazan - Room + Apartment
         */
        KazanRoom_KazanApartment { //5

            @Override
            public double changeFinalBid() {
                System.out.println("KazanRoom_KazanApartment");
                if (cadastralValue > condition_300000000) {
                    return finalBidNoMagic_2;
                } else
                    return finalBidNoMagic_0_2;
            }
        },

        /**
         * Объеденины Kazan - House + Car
         */
        //TODO ИЗМЕНЕН
        KazanHouse_KazanCar { //6

            @Override
            public double changeFinalBid() {
                System.out.println("KazanHouse_KazanCar");
                if (cadastralValue > condition_300000000) {
                    return finalBidNoMagic_2;
                } else
                    return finalBidNoMagic_0_3;
            }
        },
        /**
         * Объеденины Moscow - Room + Apartment + House
         */
        MoscowRoom_MoscowApartment_MoscowHouse { //9 10 11

            @Override
            public double changeFinalBid() {
                System.out.println("MoscowRoom_MoscowApartment_MoscowHouse");
                if (cadastralValue <= condition_10000000) {
                    return finalBidNoMagic_0_1;
                } else if ((cadastralValue <= condition_20000000) & (condition_10000000 < cadastralValue)) {
                    return finalBidNoMagic_0_15;
                } else if ((cadastralValue <= condition_50000000) & (condition_20000000 < cadastralValue)) {
                    return finalBidNoMagic_0_2;
                } else if ((cadastralValue <= condition_300000000) & (condition_50000000 < cadastralValue)) {
                    return finalBidNoMagic_0_3;
                } else if (cadastralValue > condition_300000000) {
                }
                return finalBidNoMagic_2;
            }
        },
        MoscowCar { //12

            @Override
            public double changeFinalBid() {
                System.out.println("MoscowCar");
                if (cadastralValue > condition_300000000) {
                    return finalBidNoMagic_2;
                } else
                    return finalBidNoMagic_0_1;
            }
        },
        Gorn { //14

            @Override
            public double changeFinalBid() {
                System.out.println("Gorn");
                if (cadastralValue > condition_300000000) {
                    return finalBidNoMagic_0_7;
                } else
                    return finalBidNoMagic_0_3;
            }
        }
    }
}


