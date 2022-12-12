package bf.mfptps.appgestionsconges.enums;

public enum ETrancheDemande {
	INDIVIDUEL("INDIVIDUEL", "INDIVIDUEL"),
    GROUPE("GROUPE", "GROUPE");

    protected String value;
    protected String label;

    ETrancheDemande(String value,
                  String pLabel)
    {
        this.value = value;
        this.label = pLabel;
    }

    public String getLabel()
    {
        return this.label;
    }

    public String getValue()
    {
        return this.value;
    }

    public static ETrancheDemande getByValue(String value)
    {
        for (ETrancheDemande val : ETrancheDemande.values())
        {
            if (val.getValue().equals(value))
            {
                return val;
            }
        }
        return null; //or throw new IllegalArgumentException("...");
    }

    public static String[] getLabels()
    {

        String[] labels = new String[ETrancheDemande.values().length];
        int      index  = 0;
        for (ETrancheDemande val : ETrancheDemande.values())
        {
            labels[index] = val.getLabel();
            index++ ;
        }
        return labels;
    }

    public static String getLabelByValue(String value)
    {
        return getByValue(value).getLabel();
    }

    public static ETrancheDemande getByLabel(String label)
    {
        for (ETrancheDemande val : ETrancheDemande.values())
        {
            if (val.getLabel().equals(label))
            {
                return val;
            }
        }
        return null; // ou throw new IllegalArgumentException("...");
    }

    public static String getValueByLabel(String label)
    {
        ETrancheDemande eTranslationKind = getByLabel(label);
        if (null != eTranslationKind)
        {
            return getByLabel(label).getValue();
        }
        return null;
    }
    
}
