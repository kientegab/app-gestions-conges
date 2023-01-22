package bf.mfptps.appgestionsconges.enums;

public enum EPositionDemande {
	DEMANDEUR("DEMANDEUR", "DEMANDEUR"),
	SH("SH", "Supperieur Hierarchique"),
	DRH("DRH", "Direction des ressources humaines"),
	SG("SG", "Secretariat generale"),
	DGFP("DGFP", "DGFP");

    protected String value;
    protected String label;

    EPositionDemande(String value,
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

    public static EPositionDemande getByValue(String value)
    {
        for (EPositionDemande val : EPositionDemande.values())
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

        String[] labels = new String[EPositionDemande.values().length];
        int      index  = 0;
        for (EPositionDemande val : EPositionDemande.values())
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

    public static EPositionDemande getByLabel(String label)
    {
        for (EPositionDemande val : EPositionDemande.values())
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
        EPositionDemande eTranslationKind = getByLabel(label);
        if (null != eTranslationKind)
        {
            return getByLabel(label).getValue();
        }
        return null;
    }
    
}
