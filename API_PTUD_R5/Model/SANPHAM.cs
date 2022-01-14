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
        public long Id { get; set; }

        public string TenSP { get; set; }

        public long MaLoai { get; set; }

        public long MaNCC { get; set; }

        public float GiaTien { get; set; }

        public float SL { get; set; }

        public int Min { get; set; }
    }
}
