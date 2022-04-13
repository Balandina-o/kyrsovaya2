package enums;

import java.util.Arrays;
/**
 * Класс, отвечающий за определение налоговой ставки, используемой в расчетах, в зависимости от выбранных
 * пользователем элементов выпадающего списка, позволяющего выбрать муниципальное образование и размера кадастровой стоимости
 *  объекта недвижимости
 * @author group2
 * @version 1.0
 */
public class EnumSwitch {
    
    /** Объявление числовой переменной, хранящей значение налоговой ставки*/
    public static double finalbid;
    
    /** Объявление строковых переменных, хранящихих выбранные элементы выпадающих списков */
    public static String propertyIndex, regionIndex;
    
    /** Объявление переменной для хранения значения кадастровой стоимости объекта налогооблажения */
    public  static double cadastralValue;
    /**
     * Метод-установщик значения строковой переменной, являющейся выбранным элементом выпадающего списка,
     * позволяющего выбрать тип налогооблагаемого объекта
     * @param propertyIndex - строковая переменная, являющаяся выбранным элементом выпадающего списка,
     * позволяющего выбрать тип налогооблагаемого объекта
     */
    public void setPropertyIndex(String propertyIndex) {
        this.propertyIndex = propertyIndex;
    }
    /**
     * Метод-установщик значения строковой переменной, являющейся выбранным элементом выпадающего списка,
     * позволяющего выбрать муниципальное образование
     * @param regionIndex - строковая переменная, являющейся выбранным элементом выпадающего списка,
     * позволяющего выбрать муниципальное образование
     */
    public void setRegionIndex(String regionIndex) {
        this.regionIndex = regionIndex;
    }
    /**
     * Метод-установщик значения числовой переменной, в которую помещается кадастровая стоимость
     * объекта налогооблажения
     * @param cadastralValue - кадастровая стоимость объекта налогооблажения
     */
    public void setCadastralValue(double cadastralValue) {
        this.cadastralValue = cadastralValue;
    }
    /**
     * Метод, возвращающий значение налоговой ставки
     * @return the finalbid - значение налоговой ставки
     */
    public double getFinalbid() {
        return finalbid;
    }
    /**
     * Метод для  инициализации налоговой ставки в зависимости от выбранного города и имущества.
     * Сначала реализуется поиск элемента, удоволетворяющего 2м выбранным пунктам combobox
	 * Затем происходит создание экземпляра интерфеса Selection.
	 * В зависимости от найденного элемента присваивается значение перечисление в переменную.
	 * Затем у этого значения перечисления  вызывается метод Check  который устанавливает значение налоговой ставки.
     */
    public static void enumuse() {
    	String[] gggg ={"00","01","02","03","04","10","11","12","13","14","20","21","22","23","24","30","31","32","33","34"};
    	String[] xxxx={propertyIndex,regionIndex};
    	String yyyy=xxxx[0]+xxxx[1];

    	int x=Arrays.binarySearch(gggg,yyyy);
    	if((15<=x)&(x<=19)){
    		Selection day = cityproperty.values()[15];
    		finalbid=day.Check();
    	}else{
    		Selection day = cityproperty.values()[x];
    		finalbid=day.Check();
    	}
    }    
    /** Вложенный класс перечислений cityproperty , реализовывающий интерфейс Selection */
     enum cityproperty implements Selection {  
    	 /**  элемент перечисления  UfaRoom переопределяющий метод Check интерфейса Selection */
        UfaRoom() {
            @Override
            public double Check() {
                if(cadastralValue <= 4000000) {
                    return  0.11;
                }else if((cadastralValue <= 7000000) & (4000000 < cadastralValue)) {
                    return 0.165;
                }else if((cadastralValue <= 100000000) & (7000000 < cadastralValue)) {
                    return 0.198;
                }else if((cadastralValue > 10000000)) {
                    return 0.22;
                }else if((cadastralValue > 300000000)) {
                }return 2;
            }
        },
        /**  элемент перечисления  UfaApartment переопределяющий метод Check интерфейса Selection */
        UfaApartment {
            @Override
            public double Check() {
                if(cadastralValue <= 4000000) {
                    return 0.11;
                }else if((cadastralValue <= 7000000) & (4000000 < cadastralValue)) {
                    return 0.165;
                }else if((cadastralValue <= 10000000) & (7000000 < cadastralValue)) {
                    return 0.198;
                }else if(cadastralValue > 10000000) {
                    return 0.22;
                }else if(cadastralValue > 300000000) {
                }return 2;
            }
        },
        /**  элемент перечисления  UfaHouse переопределяющий метод Check интерфейса Selection */
        UfaHouse {
            @Override
            public double Check() {
                if(cadastralValue <= 4000000) {
                    return  0.12;
                }else if((cadastralValue <= 7000000) & (4000000 < cadastralValue)) {
                    return  0.18;
                }else if((cadastralValue <= 10000000) & (7000000 < cadastralValue)) {
                    return  0.216;
                }else if(cadastralValue > 10000000) {
                    return  0.24;
                }else if(cadastralValue > 300000000) {
                }return  2;
            }
        },
        /**  элемент перечисления  UfaCar переопределяющий метод Check интерфейса Selection */
        UfaCar {
            @Override
            public double Check() {
                if(cadastralValue > 300000000) {
                    return 2;
                }else
                    return 0.11;
            }
        },

        /**  элемент перечисления  UfaOthers переопределяющий метод Check интерфейса Selection */
        UfaOthers{
            @Override
            public double Check() {
                if(cadastralValue > 300000000) {
                    return 2;
                }else
                    return 0.5;
            }
        },
        
        /**  элемент перечисления  KazanRoom переопределяющий метод Check интерфейса Selection */
        KazanRoom {
            @Override
            public double Check() {
                if(cadastralValue > 300000000) {
                    return 2;
                }else
                    return 0.2;
            }
        },
        
        /**  элемент перечисления  KazanApartment переопределяющий метод Check интерфейса Selection */
        KazanApartment {
            @Override
            public double Check() {
                if(cadastralValue > 300000000) {
                    return 2;
                }else
                    return 0.2;
            }
        },
        
        /**  элемент перечисления  KazanHouse переопределяющий метод Check интерфейса Selection */
        KazanHouse {
            @Override
            public double Check() {
                if(cadastralValue > 300000000) {
                    return  2;
                }else
                    return  0.3;
            }
        },
        
        /**  элемент перечисления  KazanCar переопределяющий метод Check интерфейса Selection */
        KazanCar {
            @Override
            public double Check() {
                if(cadastralValue > 300000000) {
                    return 2;
                }else
                    return 0.3;
            }
        },
        
        /**  элемент перечисления  KazanOthers переопределяющий метод Check интерфейса Selection */
        KazanOthers{
            @Override
            public double Check() {
                if(cadastralValue > 300000000) {
                    return 2;
                }else
                    return 0.5;
            }
        },
        
        /**  элемент перечисления  MoscowRoom переопределяющий метод Check интерфейса Selection */
        MoscowRoom {
            @Override
            public double Check() {
                if(cadastralValue <= 10000000) {
                    return 0.1;
                }else if((cadastralValue <= 20000000) & (10000000 < cadastralValue)) {
                    return 0.15;
                }else if((cadastralValue <= 50000000) & (20000000 < cadastralValue)) {
                    return 0.2;
                }else if((cadastralValue <= 300000000) & (50000000 < cadastralValue)) {
                    return 0.3;
                }else if(cadastralValue > 300000000) {
                }return 2;
            }
        },
        
        /**  элемент перечисления  MoscowApartment переопределяющий метод Check интерфейса Selection */
        MoscowApartment {
            @Override
            public double Check() {
                if(cadastralValue <= 10000000) {
                    return 0.1;
                }else if((cadastralValue <= 20000000) & (10000000 < cadastralValue)) {
                    return 0.15;
                }else if((cadastralValue <= 50000000) & (20000000 < cadastralValue)) {
                    return 0.2;
                }else if((cadastralValue <= 300000000) & (50000000 < cadastralValue)) {
                    return 0.3;
                }else if(cadastralValue > 300000000) {
                }return 2;
            }
        },
        
        /**  элемент перечисления  MoscowHouse переопределяющий метод Check интерфейса Selection */
        MoscowHouse {
            @Override
            public double Check() {
                if(cadastralValue <= 10000000) {
                    return  0.1;
                }else if((cadastralValue <= 20000000) & (10000000 < cadastralValue)) {
                    return  0.15;
                }else if((cadastralValue <= 50000000) & (20000000 < cadastralValue)) {
                    return  0.2;
                }else if((cadastralValue <= 300000000) & (50000000 < cadastralValue)) {
                    return  0.3;
                }else if(cadastralValue > 300000000) {
                }return  2;
            }
        },
        
        /**  элемент перечисления  MoscowCar переопределяющий метод Check интерфейса Selection */
        MoscowCar {
            @Override
            public double Check() {
                if(cadastralValue > 300000000) {
                    return 2;
                }else
                    return 0.1;
            }
        },
        
        /**  элемент перечисления  MoscowOthers переопределяющий метод Check интерфейса Selection */
        MoscowOthers{
            @Override
            public double Check() {
                if(cadastralValue > 300000000) {
                    return 2;
                }else
                    return 0.5;
            }
        },
        
        /**  элемент перечисления  Gorn переопределяющий метод Check интерфейса Selection */
        Gorn {
            @Override
            public double Check() {
                if(cadastralValue > 300000000) {
                    return 0.7;
                }else
                    return 0.3;
            }
        };
    }
}


