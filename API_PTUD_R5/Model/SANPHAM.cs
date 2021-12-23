using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
// <snippet_NewtonsoftJsonImport>
using Newtonsoft.Json;
// </snippet_NewtonsoftJsonImport>

namespace API_PTUD_R5.Model
{
    public class SANPHAM
    {
        [BsonId]
        public string Id { get; set; }

        public string TenSP { get; set; }

        public string MaLoai { get; set; }

        public string MaNCC { get; set; }

        public float GiaTien { get; set; }

        public int SL { get; set; }

        public int Min { get; set; }

        public int Max { get; set; }
    }
}
