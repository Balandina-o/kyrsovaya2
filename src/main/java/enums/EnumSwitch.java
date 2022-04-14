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
     * из выпадающего списка -  сумма должна составлять опр. число указанное в case /TODO продумать
     * FIXME изменить на цикл / объединить условия в методы
     * FIXME убрать Магические числа
     */
    public static void enumUse(int regionIndex, int propertyIndex) {
        //FIXME Вынести в сервлет чтобы суммировало
        int sum = propertyIndex + regionIndex;
        Selection day;
        switch (sum) {
            case 10:
                day = cityProperty.values()[0];
                finalBid = day.Check();
                break;
            case 11:
                day = cityProperty.values()[1];
                finalBid = day.Check();
                break;
            case 12:
                day = cityProperty.values()[2];
                finalBid = day.Check();
                break;
            case 13:
            case 23:
            case 33:
                day = cityProperty.values()[3];
                finalBid = day.Check();
                break;
            case 20:
                day = cityProperty.values()[4];
                finalBid = day.Check();
                break;
            case 21:
            case 22:
                day = cityProperty.values()[5];
                finalBid = day.Check();
                break;
            case 30:
            case 31:
                day = cityProperty.values()[6];
                finalBid = day.Check();
                break;
            case 32:
                day = cityProperty.values()[7];
                finalBid = day.Check();
                break;
            case 40:
            case 41:
            case 42:
            case 43:
                day = cityProperty.values()[8];
                finalBid = day.Check();
                break;
        }
        //TODO Старый код
//        String[] massList = {"00", "01", "02", "03", "04", "10", "11", "12", "13", "14", "20", "21", "22", "23", "24", "30", "31", "32", "33", "34"};
//        String[] massSelect = {propertyIndex, regionIndex};
//        String yyyy = massSelect[0] + massSelect[1];
//
//        int x = Arrays.binarySearch(massList, yyyy);
//        if ((15 <= x) & (x <= 19)) {
//             day = cityProperty.values()[15];
//            finalBid = day.Check();
//        } else {
//             day = cityProperty.values()[x];
//            finalBid = day.Check();
//        }
    }

    public static void select() {

    }

    public static void creat(int x) {
        Selection day;
        day = cityProperty.values()[x];
        finalBid = day.Check();
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
            public double Check() {
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
            public double Check() {
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
            public double Check() {
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
            public double Check() {
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
            public double Check() {
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
            public double Check() {
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
            public double Check() {
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
            public double Check() {
                System.out.println("MoscowCar");
                if (cadastralValue > 300000000) {
                    return 2;
                } else
                    return 0.1;
            }
        },
        Gorn { //14

            @Override
            public double Check() {
                System.out.println("Gorn");
                if (cadastralValue > 300000000) {
                    return 0.7;
                } else
                    return 0.3;
            }
        };
    }
}


