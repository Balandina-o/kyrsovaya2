package enums;

/**
 * @author Artyom
 * @version 2.0
 */
public class EnumSwitch {
    //test
    public static final double finalBidNoMagic = 0.11;
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
                if (cadastralValue <= 4000000) {
                    return 0.11;
                } else if ((cadastralValue <= 7000000) & (4000000 < cadastralValue)) {
                    return 0.165;
                } else if ((cadastralValue <= 10000000) & (7000000 < cadastralValue)) {
                    return 0.198;
                } else if (cadastralValue > 10000000) {
                    return 0.22;
                    //TODO что это
                } else if (cadastralValue > 300000000) {
                }
                return 2;
            }
        },
        UfaHouse { //2

            @Override
            public double changeFinalBid() {
                System.out.println("UfaHouse");
                if (cadastralValue <= 4000000) {
                    return 0.12;
                } else if ((cadastralValue <= 7000000) & (4000000 < cadastralValue)) {
                    return 0.18;
                } else if ((cadastralValue <= 10000000) & (7000000 < cadastralValue)) {
                    return 0.216;
                } else if (cadastralValue > 10000000) {
                    return 0.24;
                } else if (cadastralValue > 300000000) {
                }
                return 2;
            }
        },
        UfaCar { //3

            @Override
            public double changeFinalBid() {
                System.out.println("UfaCar");
                if (cadastralValue > 300000000) {
                    return 2;
                } else
                    return 0.11;
            }
        },

        /**
         * Объеденины Others - Ufa+Kazan+Moscow
         */
        UfaOthers_KazanOthers_MoscowOthers { //4 8 13

            @Override
            public double changeFinalBid() {
                System.out.println("UfaOthers_KazanOthers_MoscowOthers");
                if (cadastralValue > 300000000) {
                    return 2;
                } else
                    return 0.5;
            }
        },
        /**
         * Объеденины Kazan - Room + Apartment
         */
        KazanRoom_KazanApartment { //5

            @Override
            public double changeFinalBid() {
                System.out.println("KazanRoom_KazanApartment");
                if (cadastralValue > 300000000) {
                    return 2;
                } else
                    return 0.2;
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
                if (cadastralValue > 300000000) {
                    return 2;
                } else
                    return 0.3;
            }
        },
        /**
         * Объеденины Moscow - Room + Apartment + House
         */
        MoscowRoom_MoscowApartment_MoscowHouse { //9 10 11

            @Override
            public double changeFinalBid() {
                System.out.println("MoscowRoom_MoscowApartment_MoscowHouse");
                if (cadastralValue <= 10000000) {
                    return 0.1;
                } else if ((cadastralValue <= 20000000) & (10000000 < cadastralValue)) {
                    return 0.15;
                } else if ((cadastralValue <= 50000000) & (20000000 < cadastralValue)) {
                    return 0.2;
                } else if ((cadastralValue <= 300000000) & (50000000 < cadastralValue)) {
                    return 0.3;
                } else if (cadastralValue > 300000000) {
                }
                return 2;
            }
        },
        MoscowCar { //12

            @Override
            public double changeFinalBid() {
                System.out.println("MoscowCar");
                if (cadastralValue > 300000000) {
                    return 2;
                } else
                    return 0.1;
            }
        },
        Gorn { //14

            @Override
            public double changeFinalBid() {
                System.out.println("Gorn");
                if (cadastralValue > 300000000) {
                    return 0.7;
                } else
                    return 0.3;
            }
        }
    }
}


