package com.uma.supplychain.config;
import com.uma.supplychain.domain.*;
import com.uma.supplychain.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DataSeeder {
 public static final Map<String,DemoUser> USERS=new ConcurrentHashMap<>();
 public record DemoUser(String passwordHash,String role){}
 @Bean CommandLineRunner seed(InventoryRepository inventory,SupplierRepository suppliers,PasswordEncoder encoder){
  return args->{
   USERS.putIfAbsent("admin",new DemoUser(encoder.encode("Admin@123"),"ADMIN"));
   USERS.putIfAbsent("operator",new DemoUser(encoder.encode("Operator@123"),"OPERATOR"));
   USERS.putIfAbsent("viewer",new DemoUser(encoder.encode("Viewer@123"),"VIEWER"));
   if(inventory.count()==0){
    inventory.saveAll(List.of(new InventoryItem("PUMP-100","Industrial Water Pump",120,0,25,"PHL-WH1"),
     new InventoryItem("VALVE-200","Pressure Control Valve",80,0,20,"PHL-WH1"),
     new InventoryItem("FILTER-300","Inline Filter Cartridge",18,0,25,"NJ-WH2"),
     new InventoryItem("METER-400","Smart Flow Meter",250,0,40,"NJ-WH2")));
   }
   if(suppliers.count()==0)suppliers.saveAll(List.of(new Supplier("SUP-01","AquaParts Manufacturing","sales@aquaparts.example","5 business days"),
     new Supplier("SUP-02","FlowTech Components","orders@flowtech.example","8 business days")));
  };
 }
}
