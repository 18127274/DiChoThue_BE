using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
// <snippet_NewtonsoftJsonImport>
using Newtonsoft.Json;
// </snippet_NewtonsoftJsonImport>

namespace API_PTUD_R5.Model
{
    public class NHACUNGCAP
    {
        [BsonId]
        public string Id { get; set; }

        public string TenShop { get; set; }

        public string SDT { get; set; }

        public string Email { get; set; }

        public string DiaChi { get; set; }

        public decimal TinhTrang { get; set; }
    }
}
