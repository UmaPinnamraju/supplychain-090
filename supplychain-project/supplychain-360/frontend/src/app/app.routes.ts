import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard.component';
import { OrdersComponent } from './orders.component';
import { InventoryComponent } from './inventory.component';
import { ShipmentsComponent } from './shipments.component';
import { SuppliersComponent } from './suppliers.component';
import { LoginComponent } from './login.component';
import { authGuard } from './auth.guard';
export const routes:Routes=[
 {path:'login',component:LoginComponent},
 {path:'',component:DashboardComponent,canActivate:[authGuard]},
 {path:'orders',component:OrdersComponent,canActivate:[authGuard]},
 {path:'inventory',component:InventoryComponent,canActivate:[authGuard]},
 {path:'shipments',component:ShipmentsComponent,canActivate:[authGuard]},
 {path:'suppliers',component:SuppliersComponent,canActivate:[authGuard]},
 {path:'**',redirectTo:''}
];
