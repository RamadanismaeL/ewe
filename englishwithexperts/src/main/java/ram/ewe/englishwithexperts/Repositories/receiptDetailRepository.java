package ram.ewe.englishwithexperts.Repositories;

public class receiptDetailRepository {
    private int _id, _ano;
    private int _valor, _desconto, _valorPago;
    private String _reciboNr, _tipo, _estudanteID, _exmo_sr, _quantia, _metodo, _dia, _mes, _horas, _dateRegister;

    public receiptDetailRepository()
    {}

    public receiptDetailRepository(int id, String reciboNr, String tipo, String estudanteID, String exmo_sr, int valor, String quantia, int desconto, int valorPago, String metodo, String dia, String mes, int ano, String horas, String dateRegister)
    {
        this._id = id;
        this._reciboNr = reciboNr;
        this._tipo = tipo;
        this._estudanteID = estudanteID;
        this._exmo_sr = exmo_sr;
        this._valor = valor;
        this._quantia = quantia;
        this._desconto = desconto;
        this._valorPago = valorPago;
        this._metodo = metodo;
        this._dia = dia;
        this._mes = mes;
        this._ano = ano;
        this._horas = horas;
        this._dateRegister = dateRegister;
    }

    public int getId() { return this._id; }
        public String getReciboNr() { return this._reciboNr; }
            public String getTipo() { return this._tipo; }
                public String getEstudanteID() { return this._estudanteID; }
                    public String getExmoSr() { return this._exmo_sr; }
                        public int getValor() { return this._valor; }
                            public String getQuantia() { return this._quantia; }
                                public int getDesconto() { return this._desconto; }
                                    public int getValorPago() { return this._valorPago; }
                                        public String getMetodo() { return this._metodo; }
                                            public String getDia() { return this._dia; }
                                                public String getMes() { return this._mes; }
                                                    public int getAno() { return this._ano; }
                                                        public String getHoras() { return this._horas; }
}
