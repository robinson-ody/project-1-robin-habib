var fileList = document.querySelector("#file-list");
(function() {
    fetch("http://localhost:8080/api/files").then(function(response) {
        response.json().then(function(data) {
            if (Array.isArray(data) && data.length) {
                for (var i=0;i<data.length;i++) {
                    var li = document.createElement("li");
                    var name = data[i];
                    li.innerHTML = "<a href='/api/files/"+name+"'>"+name+"</a>";
                    fileList.appendChild(li);
                }
            }
        });
    })
})();

$(function(){
    var $inventory=$('#inventory');
    var $inventoryId=$('#inventoryId');
    var $detail=$('#detail');
    var $stock=$('#stock');
    var $price=$('#price');

    var inventoryList="<h2>Inventory List</h2>\n" +
        "        <div class=\"table-responsive\">\n" +
        "            <table class=\"table\" id=\"product-table\">\n" +
        "                <thead>\n" +
        "                <tr>\n" +
        "                    <th>Inventory-ID</th>\n" +
        "                    <th>Detail</th>\n" +
        "                    <th>Stock</th>\n" +
        "                    <th>Price</th>\n" +
        "                </tr>\n" +
        "                </thead>\n" +
        "                <tbody>\n" +
        "                <tr id=\"inventory\"></tr>\n" +
        "                </tbody>\n" +
        "            </table>\n" +
        "        </div>";

    var addInventory="<div>\n" +
        "            <h2>Add Inventory</h2>\n" +
        "            <p>Inventory-ID : <input type=\"text\" id=\"inventoryId\"/></p>\n" +
        "            <p>Detail : <input type=\"text\" id=\"detail\"/></p>\n" +
        "            <p>Stock : <input type=\"text\" id=\"stock\"/></p>\n" +
        "            <p>Price : <input type=\"text\" id=\"price\"/></p>\n" +
        "            <a id=\"add-inventory\" class=\"btn btn-primary\"><span class=\"glyphicon glyphicon-plus-sign\"></span> Inventory</a>\n" +
        "        </div>";

    var images="<div>\n" +
        "                    <form action=\"/api/files\" method=\"POST\" enctype=\"multipart/form-data\">\n" +
        "                        <input type=\"file\" name=\"file\">\n" +
        "                        <input type=\"submit\" value=\"Submit\">\n" +
        "                    </form>\n" +
        "                </div>\n" +
        "                <div>\n" +
        "                    <ul id=\"file-list\"></ul>\n" +
        "                </div>"

    $("#one").click(function()
    {
        $(".container-right").html(inventoryList);
    });
    $("#two").click(function()
    {
        $(".container-right").html(addInventory);
    });
    $("#three").click(function()
    {
        $(".container-right").html(images);
    });



    $.ajax({
        type:'GET',
        url:'/api/inventory',
        success:function (inventory) {
            $.each(inventory,function (i,inven) {
                $inventory.append('<th>'+inven.inventoryId+'</th>'+'<th>'+inven.detail+'</th>'+'<th>'+inven.stock+'</th>'+'<th>'+inven.price+'</th>');
            })
        },
        error:function () {
            alert('error loading inventory')
        }

    });

    $('#add-inventory').on('click',function () {
        var inven={
            inventoryId:$inventoryId.val(),
            detail:$detail.val(),
            stock:$stock.val(),
            price:$price.val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/inventory/create',
            data:inven,
            success:function (newInventory) {
                $inventory.append('<th>'+newInventory.inventoryId+'</th>'+'<th>'+newInventory.detail+'</th>'+'<th>'+newInventory.stock+'</th>'+'<th>'+newInventory.price+'</th>');
            },
            error:function () {
                alert('error adding inventory')
            }

        });
    })
});
