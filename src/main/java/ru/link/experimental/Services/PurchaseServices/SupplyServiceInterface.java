package ru.link.experimental.Services.PurchaseServices;

import ru.link.experimental.DTO.*;
import ru.link.experimental.Exceptions.PageExceptions.*;

import java.math.BigInteger;
import java.util.*;

public interface SupplyServiceInterface {

    public void create(UUID purchaseId, Long cost, String technologyStack, String structure, String comment);

    public void update(UUID id, Long cost, String technologyStack, String structure, String comment);

    public void delete(UUID id);

    public SupplyDTO get(UUID id);

    List<SupplyDTO> getPage(int pageIndex, int pageSize) throws PageIndexException, PageSizeException;
}