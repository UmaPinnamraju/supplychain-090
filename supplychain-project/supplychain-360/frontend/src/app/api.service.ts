import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Dashboard,InventoryItem,Order,Shipment,Supplier } from './models';
@Injectable({providedIn:'root'})
export class ApiService {
 constructor(private http:HttpClient){}
 dashboard(){return this.http.get<Dashboard>('/api/dashboard/summary');}
 inventory(){return this.http.get<InventoryItem[]>('/api/inventory');}
 orders(){return this.http.get<Order[]>('/api/orders');}
 createOrder(body:unknown){return this.http.post<Order>('/api/orders',body);}
 shipments(){return this.http.get<Shipment[]>('/api/shipments');}
 updateShipment(id:number,status:string,notes:string){return this.http.patch<Shipment>(`/api/shipments/${id}/status`,{status,notes});}
 suppliers(){return this.http.get<Supplier[]>('/api/suppliers');}
}
