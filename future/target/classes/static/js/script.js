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

    $("#one").click(function()
    {
        $(".container-right").html(inventoryList);
    });
    $("#two").click(function()
    {
        $(".container-right").html(addInventory);
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
