using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Bson.Serialization.IdGenerators;
// <snippet_NewtonsoftJsonImport>
using Newtonsoft.Json;
// </snippet_NewtonsoftJsonImport>

namespace API_PTUD_R5.Model
{
    public class NHACUNGCAP
    {
        [BsonId]
        public long Id { get; set; }

        public string TenShop { get; set; }

        public string SDT { get; set; }

        public string Email { get; set; }

        public string DiaChi { get; set; }

        public string TenTaiKhoan { get; set; }

        public string MatKhau { get; set; }

        public decimal TinhTrang { get; set; }
    }
}
