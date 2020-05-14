import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { ProductService } from "../serviec/ProductService/product-service";
import { ProductModel, ProductInfo } from "../Model/Products";
import { map, min } from "rxjs/operators";
import { CartService } from "../serviec/shopCart/cart.service";
import { HttpService, url } from "../serviec/HttpSerivce/http.service";

@Component({
  selector: "app-product",
  templateUrl: "./product.component.html",
  styleUrls: ["./product.component.scss"],
})
export class ProductComponent implements OnInit {
  // 产品详情
  product: ProductModel;
  show_select = false;
  step = 1;
  buynumber = 1;
  seleType: number;
  productInfoFlag: boolean = true;

  constructor(
    private routerinfo: ActivatedRoute,
    private productService: ProductService,
    public cartService: CartService,
    private httpClient: HttpService
  ) {
    this.product = new ProductModel();
  }

  ngOnInit() {
    let proId = this.routerinfo.snapshot.queryParams["id"];

    console.log(proId);

    // 获取产品详情
    this.productService
      .Get(proId)
      .pipe(
        map((q) => q[0]),
        map(
          (q) =>
            new ProductModel(
              q.id,
              q.name,
              q.money,
              q.homePage,
              q.pages.split(","),
              q.info,
              q.infos
            )
        )
      )
      .subscribe((re) => {
        console.log(re);
        Object.assign(this.product, re);
        console.log(this.product);
      });
  }

  /*   changeNum(num) {
    if (num === -1 && this.buynumber !== 1) {
      this.buynumber -= 1;
    } else if(num === 1) {
      this.buynumber += 1;
    }
  } */

  ClickDisplay(): void {
    this.productInfoFlag = true;
  }

  ClickInfo(): void {
    this.productInfoFlag = false;
  }

  ByNext(): void {
    this.cartService.order.money = this.product.money * this.buynumber;
  }

  // 获取服务器文件
  getPic(picUrl: string): string {
    return url + picUrl;
  }

  selectProduct(productinfoId: number, price: number) {
    this.cartService.order.productInfoId = productinfoId;
    this.product.money = price;
    this.cartService.order.money = price;
    this.cartService.order.name = this.product.name;
  }

  /*   buynext() {
    if (this.step === 1 ) {
      this.step = 2;
    } else if (this.step === 2) {
      this.show_select = false;
      this.step = 1;
    }
  } */
}
