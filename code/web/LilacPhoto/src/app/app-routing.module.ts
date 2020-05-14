import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { ProductComponent } from './product/product.component';
import { OrderComponent } from './order/order.component';
import { MineComponent } from './mine/mine.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { AdressComponent } from './adress/adress.component';


const routes: Routes = [
  { path: 'index', component: IndexComponent },
  { path: 'product', component: ProductComponent },
  { path: 'order', component: OrderComponent },
  { path: 'mine', component: MineComponent },
  { path: 'appointment', component: AppointmentComponent },
  { path: "Adress", component: AdressComponent },
  { path: '', component: IndexComponent },
  { path: '**', component: IndexComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
