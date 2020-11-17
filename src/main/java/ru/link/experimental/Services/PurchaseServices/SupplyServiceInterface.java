package ru.link.experimental.Services.PurchaseServices;

import ru.link.experimental.DTO.*;
import ru.link.experimental.Exceptions.PageExceptions.*;
import ru.link.experimental.Exceptions.SupplyException;

import java.math.BigInteger;
import java.util.*;

public interface SupplyServiceInterface {

    public void create(UUID purchaseId, Long cost, String technologyStack, String structure, String comment) throws SupplyException;

    public void update(UUID id, Long cost, String technologyStack, String structure, String comment) throws SupplyException;

    public void delete(UUID id) throws SupplyException;

    public SupplyDTO get(UUID id) throws SupplyException;

    List<SupplyDTO> getPage(int pageIndex, int pageSize) throws PageIndexException, PageSizeException;
}