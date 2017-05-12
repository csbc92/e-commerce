package ecompim.ERPAccess;

import ecompim.Product.DetailedProduct;
import ecompim.Product.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vedsted on 24-04-2017.
 */
public class ERPDummyDB {
    /**
     * Generates a map of static products
     * @return a map with integers as key and DetailedProducts as values
     */
    public static Map<Integer, DetailedProduct> getProducts() {
        Map<Integer, DetailedProduct> products = new HashMap<>();


        products.put(1, new DetailedProduct(1, "MSI Z270I Gaming Pro Carbon AC, S-1151" +
                " Bundkort, mini-ITX, Z270, DDR4, 1xPCIe-x16, M.2, WiFi, Mystic Light Sync", 1649, "MSI Z270I Gaming Pro Carbon AC", 50,3,4,5,5));
        HashMap<String, String> details = new HashMap<>();
        details.put("Socket", "LGA-1151");
        details.put("Ram-types", "DDR4, DDR3L");
        details.put("Form factor","mini-ITX");

        products.get(1).setTechnicalDetails(details);
       // products.get(1).setRating(4.2);
        //products.get(1).setTags(new String[]{"hardware", "motherboard", "msi"});

        products.put(2, new DetailedProduct(2, "AMD Ryzen 7 1700 Processor" +
                " Socket-AM4, 8-Core, 16-Thread, 3.0/3.7GHz, 65W, inkl. køler", 2690, "AMD Ryzen 7 1700", 34,43,56,22,1));
        details = new HashMap<>();
        details.put("Manufacturer", "AMD");
        details.put("Generation", "Ryzen");
        products.get(2).setTechnicalDetails(details);
        //products.get(2).setRating(3.8);
        //products.get(2).setTags(new String[]{"hardware", "cpu", "ryzen", "amd"});


        products.put(3, new DetailedProduct(3, "Corsair Obsidian 900D Big Tower Sort" +
                "Blæsere: 3x 120mm Front, 1x 140mm Bag, M/E-ATX, m-ITX, HPTX, USB2.0/3, Vindue", 2999, "Corsair Obsidian 900D", 16,43,56,22,1));
        details = new HashMap<>();
        products.get(3).setTechnicalDetails(details);
//        products.get(3).setRating(4.0);
//        products.get(3).setTags(new String[]{"hardware", "cabinet", "corsair", "giant"});
//
        products.put(4, new DetailedProduct(4, "MSI GeForce GTX 1080 Ti Sea Hawk X\n" +
                "Grafikkort, PCI-Express 3.0, 11GB GDDR5X, 1544/1657MHz, Water cooled, Pascal", 7790, "MSI GeForce GTX 1080 Ti Sea Hawk X", 6,43,56,22,1));
        details = new HashMap<>();
        products.get(4).setTechnicalDetails(details);

        products.put(5, new DetailedProduct(5, "ASUS GeForce GTX 1080 Ti \n" +
                "Grafikkort, PCI-Express 3.0, 11GB GDDR5X, 1544/1657MHz, Water cooled, Pascal", 7290, "ASUS GeForce GTX 1080 Ti", 6,43,56,22,1));
        details = new HashMap<>();
        products.get(5).setTechnicalDetails(details);

        products.put(6, new DetailedProduct(6, "Corsair Obsidian 750D Full Tower" +
                "Blæsere: 3x 120mm Front, 1x 140mm Bag, M/E-ATX, m-ITX, HPTX, USB2.0/3, Vindue", 1999, "Corsair Obsidian 750D Full Tower", 16,43,56,22,1));
        details = new HashMap<>();
        products.get(6).setTechnicalDetails(details);

        products.put(7, new DetailedProduct(7, "Intel i7 6700K Processor" +
                " LGA1151, 6-Core, 16-Thread, 3.0/3.7GHz, 65W, inkl. køler", 2190, "Intel i7 6700K", 34,43,56,22,1));
        details = new HashMap<>();
        products.get(7).setTechnicalDetails(details);

        products.put(8, new DetailedProduct(8, "Intel i7 7700K Processor" +
                " LGA1151, 6-Core, 16-Thread, 3.0/3.7GHz, 65W, inkl. køler", 2690, "Intel i7 7700K", 34,43,56,22,1));
        details = new HashMap<>();
        products.get(8).setTechnicalDetails(details);

        products.put(9, new DetailedProduct(9, "Intel i5 6500K Processor" +
                " LGA1151, 6-Core, 16-Thread, 3.0/3.7GHz, 65W, inkl. køler", 1690, "Intel i5 6500K", 34,43,56,22,1));
        details = new HashMap<>();
        products.get(9).setTechnicalDetails(details);

        //TODO Products may be added here
        return products;
    }
}
