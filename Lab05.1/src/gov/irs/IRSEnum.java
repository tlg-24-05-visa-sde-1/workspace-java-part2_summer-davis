/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */

package gov.irs;

import java.util.ArrayList;
import java.util.Collection;

enum IRSEnum implements IRS {
    INSTANCE;

    // BUSINESS CODE
    private Collection<TaxPayer> payers = new ArrayList<>();

    public void collectTaxes() {
        for (TaxPayer payer : payers) {
            payer.payTaxes();
        }
    }

    public void register(TaxPayer payer) {
        payers.add(payer);
    }
}