import { Component, OnInit } from '@angular/core';
import { from } from 'rxjs';
import {SelectItem} from 'primeng/api';
import { PrimeNGConfig } from 'primeng/api';
import { acceuilservice } from './acceuilservice';
import { Product } from './product';
@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent implements OnInit {
  
  products: Product[];

  sortOptions: SelectItem[];

  sortOrder: number;

  sortField: string;

  constructor(private productService: acceuilservice, private primengConfig: PrimeNGConfig) { }

  ngOnInit() {
      this.productService.getProducts().then(data => this.products = data);

      this.sortOptions = [
          {label: 'Price High to Low', value: '!price'},
          {label: 'Price Low to High', value: 'price'}
      ];

      this.primengConfig.ripple = true;
  }
  
  onSortChange(event) {
      let value = event.value;

      if (value.indexOf('!') === 0) {
          this.sortOrder = -1;
          this.sortField = value.substring(1, value.length);
      }
      else {
          this.sortOrder = 1;
          this.sortField = value;
      }
  }

}
