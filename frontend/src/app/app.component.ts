import { Component, Inject } from '@angular/core';
import { ProductService } from './services/product.service';
import { Product } from './models/Product.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Coffee Shop';
  products: Product[];

  constructor(private productService: ProductService) {
    console.log(productService.getProducts());
  }

  ngOnInit() {
    this.productService.getProducts().subscribe(
      data => {
        this.products = data.content;
      }
    );
  }
}
