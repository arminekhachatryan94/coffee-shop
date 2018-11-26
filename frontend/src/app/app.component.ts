import { Component, HostListener } from '@angular/core';
import { ProductService } from './services/product.service';
import { Product } from './models/product.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Coffee Shop';
  products: Product[];

  edit_index: Number;
  edit_name: String;

  new_value;

  constructor(private productService: ProductService) {
  }

  ngOnInit() {
    this.productService.getProducts().subscribe(
      data => {
        this.products = data.content;
        console.log(data);
      }
    );
  }

  onEditClick(index, name) {
    console.log(index + " " + name);
    this.edit_index = index;
    this.edit_name = name;
    if(name == "name") {
      this.new_value = this.products[index].name;
    } else if(name == "size") {
      this.new_value = this.products[index].size;
    } else if(name == "price") {
      this.new_value = this.products[index].price;
    }
  }

  onKeydown() {
    this.edit_index = -1;
    this.edit_name = "";
    this.new_value = undefined;
  }

  onDelete(index) {
    console.log("delete requested " + index);
    this.productService.deleteProduct(this.products[index].productId)
      .subscribe(
        data => {
          let message = data.message;
          let level = data.level;
          if(level == "success") {
            this.products.splice(index, 1);
          } else {
            alert(message);
          }
        }
      );
  }

}
