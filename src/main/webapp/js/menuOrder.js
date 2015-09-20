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
		}
		
		var plist = document.getElementsByClassName("product");
		console.log(plist.length);
		if(plist.length==0) {
			$(".cartresume").hide();
		} else {
			var i;
			var subtotal=0;
			/*for(i=0 ; i<plist.length; i++) {
				console.log(plist[i].children(".precio").text());
				subtotal += Float.parseFloat(plist[i].children(".precio").text().substring(2));
			}
			$(".subtotal").append(subtotal);*/
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