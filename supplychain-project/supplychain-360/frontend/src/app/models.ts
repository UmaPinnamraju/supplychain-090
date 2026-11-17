export interface Dashboard { openOrders:number; lowStockSkus:number; activeShipments:number; deliveredShipments:number; }
export interface InventoryItem { id:number; sku:string; name:string; onHand:number; reserved:number; available:number; reorderPoint:number; warehouse:string; lowStock:boolean; }
export interface OrderLine { sku:string; quantity:number; }
export interface Order { id:number; customerName:string; customerEmail:string; shippingAddress:string; status:string; createdAt:string; lines:OrderLine[]; }
export interface Shipment { id:number; orderId:number; customerName:string; trackingNumber:string; carrier:string; status:string; updatedAt:string; }
export interface Supplier { id:number; code:string; name:string; contactEmail:string; leadTime:string; active:boolean; }
