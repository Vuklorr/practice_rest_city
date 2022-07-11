package com.city.practice_city.util;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

import java.util.UUID;

public class ParseUUID extends CellProcessorAdaptor {
    @Override
    public <T> T execute(Object o, CsvContext csvContext) {
        validateInputNotNull(o, csvContext);

        try {
            return next.execute(UUID.fromString(o.toString()), csvContext);
        }
        catch (Exception e) {
            throw new SuperCsvCellProcessorException(
                    String.format("Could not parse '%s' as a UUID", o), csvContext, this);
        }
    }
}
