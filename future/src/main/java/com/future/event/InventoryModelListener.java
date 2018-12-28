/*
package com.future.event;

import com.future.model.Inventory;
import com.future.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class InventoryModelListener extends AbstractMongoEventListener<Inventory> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public InventoryModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Inventory> event) {
        event.getSource().setId(sequenceGenerator.generateSequence(Inventory.SEQUENCE_NAME));
    }


}
*/
