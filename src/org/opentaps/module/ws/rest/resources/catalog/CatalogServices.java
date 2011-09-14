package org.opentaps.module.ws.rest.resources.catalog;

import javolution.util.FastList;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.UtilMisc;
import org.ofbiz.base.util.UtilValidate;
import org.ofbiz.entity.GenericDelegator;
import org.ofbiz.entity.GenericEntityException;
import org.ofbiz.entity.GenericValue;
import org.ofbiz.entity.util.EntityUtil;

import org.ofbiz.product.catalog.CatalogWorker;
import org.ofbiz.product.category.CategoryWorker;

import javax.servlet.ServletRequest;
import java.util.List;

public class CatalogServices extends CategoryWorker {

    public static GenericValue getCatalogTopCategory(GenericDelegator delegator, String prodCatalogId) {
        if (prodCatalogId == null || prodCatalogId.length() <= 0) return null;

        List<GenericValue> prodCatalogCategories = CatalogWorker.getProdCatalogCategories(delegator, prodCatalogId, "PCCT_BROWSE_ROOT");

        if (UtilValidate.isNotEmpty(prodCatalogCategories)) {
            GenericValue prodCatalogCategory = EntityUtil.getFirst(prodCatalogCategories);

            try {
                prodCatalogCategory = prodCatalogCategory.getRelatedOneCache("ProductCategory");
            } catch (GenericEntityException e) {
                Debug.logWarning(e.getMessage(), module);
//                prodCatalogCategory = null;
            }

            return prodCatalogCategory;
        } else {
            return null;
        }
    }

    public static List<GenericValue> getRelatedCategoriesRet(GenericDelegator delegator, String attributeName, String parentId, boolean limitView, boolean excludeEmpty, boolean recursive) {
        List<GenericValue> categories = FastList.newInstance();

        if (Debug.verboseOn()) Debug.logVerbose("[CategoryWorker.getRelatedCategories] ParentID: " + parentId, module);

//        GenericDelegator delegator = (GenericDelegator) request.getAttribute("delegator");
        List<GenericValue> rollups = null;

        try {
            rollups = delegator.findByAndCache("ProductCategoryRollup",
                        UtilMisc.toMap("parentProductCategoryId", parentId),
                        UtilMisc.toList("sequenceNum"));
            if (limitView) {
                rollups = EntityUtil.filterByDate(rollups, true);
            }
        } catch (GenericEntityException e) {
            Debug.logWarning(e.getMessage(), "CatalogServices");
            rollups = null;
        }
        if (UtilValidate.isNotEmpty(rollups)) {
//            Debug.log("Rollup size: " + rollups.size() + " == " + rollups, module);
            for (GenericValue parent: rollups) {
                // Debug.log("Adding child of: " + parent.getString("parentProductCategoryId"), module);
                GenericValue cv = null;

                try {
                    cv = parent.getRelatedOneCache("CurrentProductCategory");
                } catch (GenericEntityException e) {
                    Debug.logWarning(e.getMessage(), "CatalogServices");
                    cv = null;
                }
                if (cv != null) {
                    if (excludeEmpty) {
                        if (!isCategoryEmpty(cv)) {
                            //Debug.log("Child : " + cv.getString("productCategoryId") + " is not empty.", module);
                            categories.add(cv);
                            if (recursive) {
                                categories.addAll(getRelatedCategoriesRet(delegator, attributeName, cv.getString("productCategoryId"), limitView, excludeEmpty, recursive));
                            }
                        }
                    } else {
                        categories.add(cv);
                        if (recursive) {
                            categories.addAll(getRelatedCategoriesRet(delegator, attributeName, cv.getString("productCategoryId"), limitView, excludeEmpty, recursive));
                        }
                    }
                }
            }
        }
        return categories;
    }

}
