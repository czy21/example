import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { ProductComponent } from './product/product.component';
import { OrderComponent } from './order/order.component';
import { MineComponent } from './mine/mine.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { HttpService } from './serviec/HttpSerivce/http.service';
import { HttpClientModule } from '@angular/common/http';
import { ProductService } from './serviec/ProductService/product-service';
import { ShopService } from './serviec/ShopService/shop-service';
import { CartService } from './serviec/shopCart/cart.service';
import { AdressComponent } from './adress/adress.component';
import { OderService } from './serviec/OrderService/oder.service';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ProductComponent,
    OrderComponent,
    MineComponent,
    AppointmentComponent,
    AdressComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [ProductService,ShopService,HttpService,CartService,OderService],
  bootstrap: [AppComponent]
})
export class AppModule { }
