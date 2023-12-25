import { Component } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../common/product';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',
  // templateUrl: './product-list-table.component.html'
  styleUrl: './product-list.component.css'
})
export class ProductListComponent {

  products: Product[] = [];
  currentCategoryId: number = 1;
  previousCategoryId: number = 1;
  searchMode: boolean = false;

  // properties for server side paging
  thePageNumber: number = 1;
  thePageSize: number = 10;
  theTotalElements: number = 0;

  constructor(private productService: ProductService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    });
  }
  // listProducts() {

  //   // check if "id" parameter is available
  //   const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');
  //   if (hasCategoryId) {
  //     // get the "id" param string. convert string to a number using the "+" symbol
  //     // the "+" symbol is a shortcut to convert string values to numbers
  //     // "!" is a TypeScript symbol to tell the compiler that 
  //     // the value is not null or undefined
  //     this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
  //   }
  //   else {
  //     // not category id available ... default to category id 1
  //     this.currentCategoryId = 1;
  //   }

  //   this.productService.getProductList(this.currentCategoryId).subscribe(
  //     data => {
  //       this.products = data;
  //     }
  //   );
  // }

  listProducts() {

    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if (this.searchMode) {
      this.handleSearchProducts();
    }
    else {
      this.handleListProducts();
    }

  }

  handleSearchProducts() {

    const theKeyword: string = this.route.snapshot.paramMap.get('keyword')!;

    // now search for the products using keyword
    this.productService.searchProducts(theKeyword).subscribe(
      data => {
        this.products = data;
      }
    )
  }

  handleListProducts() {

    // check if "id" parameter is available
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    if (hasCategoryId) {
      // get the "id" param string. convert string to a number using the "+" symbol
      // the "+" symbol is a shortcut to convert string values to numbers
      // "!" is a TypeScript symbol to tell the compiler that 
      // the value is not null or undefined
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
    }
    else {
      // not category id available ... default to category id 1
      this.currentCategoryId = 1;
    }

    // check if we have a different category than previous
    // Note: Angular will reuse a component if it is currently being viewed
    // if we have a different category id than previous
    // then set thePageNumber back to 1
    if (this.previousCategoryId != this.currentCategoryId) {
      this.thePageNumber = 1;
    }

    this.previousCategoryId = this.currentCategoryId;

    console.log(`currentCategoryId=${this.currentCategoryId}, thePageNumber=${this.thePageNumber}`);

    // now get the products for the given category id
    this.productService.getProductListPaginate(
      this.thePageNumber - 1,
      this.thePageSize,
      this.currentCategoryId)
      .subscribe(
        data => {
          this.products = data._embedded.products;
          this.thePageNumber = data.page.number + 1; // Spring Data REST is zero based
          this.thePageSize = data.page.size;
          // now set the total elements
          this.theTotalElements = data.page.totalElements;
        }
      );   
  }
}
