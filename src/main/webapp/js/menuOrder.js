$(document).ready(function(){
	$(".select-dish").change(function() {
		var p = $(this).parent().parent().children(".price").text();
		$("#"+this.name+"").remove();
		if ($(this).val() !== '0') {
			var cant = "<div class='col-sm-3 cant'> "+$(this).val()+" x </div>";
			var nombre = "<div class='col-sm-5 nombre'>"+this.name+"</div>";
			var precio = "<div class='col-sm-4 precio'>"+p+"</div>";
			var product = $("<div class='product' id='"+this.name+"'>"+cant+nombre+precio+"</div>");
			$(".productContainer").prepend(product);
		}else{
			var aux_id;
			aux_id = document.getElementById(this.name);
			while(aux_id != null){
				aux_id.remove();
				aux_id = document.getElementById(this.name);
			}
		}
		
		var plist = document.getElementsByClassName("product");
		console.log(plist.length);
		if(plist.length==0) {
			$(".cartresume").hide();
		} else {
			var i;
			var subtotal=0;
			var total;
			
			$(".product").each(function(index) {
				var aux = $(this).children(".cant").text();
				var aux1 = aux.substring(0,aux.length-3);
				var aux2 = $(this).children(".precio").text();
				var aux3 = aux2.substring(2,aux2.length);
				subtotal = subtotal + parseInt(aux1)*parseFloat(aux3);
			});
			
			var aux4 = $("#sprice").text();
			var aux5 = aux4.substring(2,aux4.length);
			var costomin = parseInt(aux5);
			var total = subtotal+costomin;
			
			
			$("#subprice").empty();
			$("#subprice").text("$ "+subtotal.toFixed(1));
			$("#tprice").empty();
			$("#tprice").text("$ "+total.toFixed(1)+"");
			
			
			$(".cartresume").removeClass("hide");
		}
		
	});
	
	
});

/*
<div class="product" id="name">
	<div class="col-sm-3 cant" value="CANT">CANT X</div>
	<div class="col-sm-6 nombre">NOMBRE</div>
	<div class="col-sm-3 precio" value="PRECIO">PRECIO</div>
</div>
<div class="subtotal"></div>
<div class="costoEnvio"></div>
<div class="total"></div>
*/