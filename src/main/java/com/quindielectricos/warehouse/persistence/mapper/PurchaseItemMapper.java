package com.quindielectricos.warehouse.persistence.mapper;

import com.quindielectricos.warehouse.domain.PurchaseItem;
import com.quindielectricos.warehouse.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {
    @Mappings({@Mapping(source = "id.idProducto", target = "productId"),
    @Mapping(source = "cantidad", target = "quantity"),
    @Mapping(source = "estado", target = "active")

    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);


    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })

ComprasProducto toComprasProducto(PurchaseItem item);
}
