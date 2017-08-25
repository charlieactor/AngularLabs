angular.module('appModule')
			  .component('productList', {
				  templateUrl : 'app/appModule/productList.component.html',
				  controller  : function(productService) {
					  //Step 1 - label vm as the component (also the controller?)
					  var vm = this;
					  
					  vm.products = productService.index();
					  
					  vm.getNumItems = function() {
						return vm.products.length;  
					  };
					  
					  vm.log = function(string) {
						  console.log(string);
					  };
					  
					  vm.addItem = function(prod) {
						  productService.create(prod);
					  }
					  
					  
				  },
			  	 controllerAs : "vm"
			  });